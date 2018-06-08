package io.github.symonk.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java8.En;
import io.github.symonk.webdriver.Driver;
import org.springframework.beans.factory.annotation.Autowired;


public class GeneralSteps implements En {

    @Autowired private Driver driver;

    @Given("^Something or other$")
    public void something_or_other() {
        driver.get("http://www.bbc.co.uk");
    }


}
