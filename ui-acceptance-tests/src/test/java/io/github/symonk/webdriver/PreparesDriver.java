package io.github.symonk.webdriver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface PreparesDriver {

  RemoteWebDriver getWebDriver(MutableCapabilities capabilities);
}
