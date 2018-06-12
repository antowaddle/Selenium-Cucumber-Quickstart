package io.github.symonk.spring;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.symonk.cucumber.TestWorld;
import io.github.symonk.helpers.localisation.ResourceHelper;
import io.github.symonk.helpers.logging.LogHelper;
import io.github.symonk.helpers.logging.Loggable;
import io.github.symonk.pageobjects.PuppyAdoptionHomePage;
import io.github.symonk.runvalidation.TestExecutionListener;
import io.github.symonk.webdriver.Driver;
import io.github.symonk.webdriver.DriverSupplier;
import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource(value = {"classpath:/framework.properties"})
public class SpringConfiguration {

  static {
    WebDriverManager.chromedriver().setup();
  }

  @Bean
  public PuppyAdoptionHomePage puppyAdoptionHomePage() {
    return new PuppyAdoptionHomePage(driver(), explicitWait(), properties().getBaseEnvironmentUrl());
  }

  @Bean
  public WebDriverWait explicitWait() {
    return new WebDriverWait(driver(), properties().getExplicitWaitTimeout());
  }

  @Bean
  public TestExecutionListener testExecutionListener() {
    return new TestExecutionListener();
  }

  @Bean
  public Driver driver() {
    return new Driver(driverSupplier());
  }

  @Bean DriverSupplier driverSupplier() {
    return new DriverSupplier(properties());
  }

  @Bean
  public AutomationProperties properties() {
    return new AutomationProperties();
  }

  @Bean
  public SlackApi slackHelper() {
    return new SlackApi(properties().getSlackApiToken());
  }

  @Bean
  public Loggable logHelper() {
    return new LogHelper();
  }

  @Bean
  public ResourceHelper resourceHelper() {
    return new ResourceHelper(properties().getLanguage());
  }

  @Bean
  public TestWorld testWorld() {
    return new TestWorld();
  }
}
