package io.github.anthony.webdriver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

public enum DriverType implements PreparesDriver {
  CHROME {
    public RemoteWebDriver getWebDriver(MutableCapabilities capabilities) {
      return new ChromeDriver(getChromeCapabilities());
    }
  },
  FIREFOX {
    public RemoteWebDriver getWebDriver(MutableCapabilities capabilities) {
      return new FirefoxDriver(getFirefoxCapabilities());
    }
  };

  public static FirefoxOptions getFirefoxCapabilities() {
    FirefoxOptions options = new FirefoxOptions();
    options.addPreference("marionette", true);
    return options;
  }

  public static ChromeOptions getChromeCapabilities() {
    ChromeOptions options = new ChromeOptions();
    Map<String, Object> chromePreferences = new HashMap<>();
    chromePreferences.put("profile.password_manager_enabled", false);
    options.addArguments("--no-default-browser-check");
    options.addArguments("--start-maximized");
    options.setExperimentalOption("prefs", chromePreferences);

    return options;
  }
}
