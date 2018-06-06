package io.github.symonk.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Driver extends EventFiringWebDriver {

    private static WebDriver WRAPPED_WEBDRIVER;

    public Driver(WebDriver driver) {
        super(driver);
    }
}
