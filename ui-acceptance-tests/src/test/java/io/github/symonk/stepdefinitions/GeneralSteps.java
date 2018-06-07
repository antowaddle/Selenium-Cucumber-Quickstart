package io.github.symonk.stepdefinitions;

import cucumber.api.java.en.Given;
import io.github.symonk.webdriver.DriverFactory;
import org.openqa.selenium.WebDriver;


public class GeneralSteps {

    private WebDriver driver = DriverFactory.getInstance().getDriver();

    @Given("^Something or other$")
    public void some_or_other() throws Exception {
        driver.get("http://www.bbc.co.uk");
        Thread.sleep(1500);
    }

}
