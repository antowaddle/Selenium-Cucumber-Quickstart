package io.github.anthony.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.anthony.helpers.localisation.ResourceHelper;
import io.github.anthony.spring.AutomationProperties;
import io.github.anthony.webdriver.Driver;
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

  @Then("^the price of adoption is (.+)$")
  public void thePriceOfAdoptionIs$(int arg0) {

  }
}
