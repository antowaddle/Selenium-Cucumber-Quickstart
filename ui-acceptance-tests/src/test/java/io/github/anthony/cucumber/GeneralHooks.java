package io.github.anthony.cucumber;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.anthony.helpers.logging.Loggable;
import io.github.anthony.spring.SpringConfiguration;
import io.github.anthony.webdriver.Driver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackException;
import net.gpedro.integrations.slack.SlackMessage;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

@Slf4j
@ContextConfiguration(classes = SpringConfiguration.class)
public class GeneralHooks {

  private Driver driver;
  private Loggable logger;
  private SlackApi slackService;

  @Autowired
  public GeneralHooks(Driver driver, SlackApi slackService, Loggable logger) {
    this.driver = driver;
    this.logger = logger;
    this.slackService = slackService;
  }

  @Before
  @Step("Initialising logger")
  public void initialise(Scenario scenario) {
    startLogger(scenario);
  }

  @After()
  @Step("Attaching debugging artifacts when necessary")
  public void checkForFailures(Scenario scenario) {
    log.info("Scenario: " + scenario.getName() + " ended");
    if (scenario.isFailed()) {
      notifySlackOfFailure(scenario);
      attachPageSourceIfNecessary(scenario);
      getScreenshotAsAttachment();
    }
    attachJsonLog(scenario);
    stopLogger();
    clearSessionData();
  }

  private void attachPageSourceIfNecessary(Scenario scenario) {
    if (scenario.getSourceTagNames().stream().noneMatch(str -> str.trim().equals("@exclude-page-source"))) {
      log.info("Attaching page source because scenario was not annotated with @exclude-page-source cucumber tag");
      getPageSourceAsAttachment();
    }
  }

  private void attachJsonLog(Scenario scenario) {
    try {
      getJsonLogAsAttachment(scenario.getName());
    } catch (IOException exception) {
      log.error("Exception occurred when attaching failed scenario log files", exception);
    }
  }

  @Attachment(value = "Screenshot", type = "image/png")
  private byte[] getScreenshotAsAttachment() {
    log.info(("Adding screenshot: Failure screenshot"));
    return (driver).getScreenshotAs(OutputType.BYTES);
  }

  @Attachment(value = "Page Source", type = "text/plain")
  private String getPageSourceAsAttachment() {
    log.info(("Adding pagesource: Failed page source"));
    return driver.getPageSource();
  }

  @Attachment(value = "Test Log", type = "application/json")
  private byte[] getJsonLogAsAttachment(String scenarioName) throws IOException {
    File[] files = listFilesMatching(new File("target/test_logs/"), scenarioName + "-.*\\.json");
    log.info("Adding log file: " + files[0].getPath());
    return Files.readAllBytes(Paths.get(files[0].getAbsolutePath()));
  }

  private void notifySlackOfFailure(Scenario scenario) {
    log.error("The following scenario resulted in failure: " + scenario.getName());
    try {
      slackService.call(new SlackMessage("[Automation scenario failure]: " + scenario.getName()));
    } catch (SlackException exception) {
      log.error(
          "Slack is not configured, check the slack-token property in 'framework.properties'");
    }
  }

  private void startLogger(Scenario scenario) {
    log.info("Starting up the logger instance");
    logger.startLogging(scenario.getName());
  }

  private void stopLogger() {
    log.info("Stopping up the logger instance");
    logger.stopLogging();
  }

  private void clearSessionData() {
    log.info("Deleting cookie(s) for the current domain");
    driver.clearCookies();
    log.info("Delete both local and session storage data");
    driver.clearLocalStorage();
    driver.clearSessionStorage();
  }

  private File[] listFilesMatching(File root, String regex) {
    if (!root.isDirectory()) {
      throw new IllegalArgumentException(root + " is not a directory.");
    }
    final Pattern p = Pattern.compile(regex);
    return root.listFiles(file -> p.matcher(file.getName()).matches());
  }
}
