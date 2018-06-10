package io.github.symonk.stepdefinitions;

import cucumber.api.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.symonk.helpers.localisation.ResourceHelper;
import io.github.symonk.spring.AutomationProperties;
import io.github.symonk.webdriver.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class GeneralSteps {

  @Autowired private Driver driver;
  @Autowired private AutomationProperties properties;
  @Autowired private ResourceHelper resourceHelper;

  @Given("^Something or other$")
  public void something_or_other() {
    driver.get(properties.getBaseEnvironmentUrl());
    log.info("language settings:" + resourceHelper.getResourceValue("foo"));
  }
}
