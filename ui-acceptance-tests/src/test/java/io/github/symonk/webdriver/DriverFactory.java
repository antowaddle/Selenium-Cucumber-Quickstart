package io.github.symonk.webdriver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Slf4j
public class DriverFactory {

    private DriverFactory() {

    }

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> getDriverType());

    public WebDriver getDriver(){
        return driver.get();
    }

    public void quitDriver(){
        driver.get().quit();
        driver.remove();
    }

    public static WebDriver getDriverType() {
        return new ChromeDriver();
    }



}
