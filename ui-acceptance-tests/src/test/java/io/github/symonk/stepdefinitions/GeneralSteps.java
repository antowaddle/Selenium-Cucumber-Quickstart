package io.github.symonk.stepdefinitions;

import cucumber.api.java.en.Given;
import io.github.symonk.webdriver.Driver;
import org.springframework.beans.factory.annotation.Autowired;


public class GeneralSteps {

    @Autowired private Driver driver;

    @Given("^Something or other$")
    public void some_or_other() throws Exception {
        driver.get("http://www.bbc.co.uk");
        Thread.sleep(1500);
    }

}
