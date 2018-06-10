package io.github.symonk.spring;

import io.github.symonk.helpers.localisation.LanguageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class AutomationProperties {

  @Autowired private Environment environment;

  public String getBaseEnvironmentUrl() {
    return environment.getProperty("application.baseurl");
  }

  public String getSlackApiToken() {
    return environment.getProperty("slack.api.token").toUpperCase();
  }

  public boolean getRunOnSeleniumGrid() {
    return Boolean.valueOf(environment.getProperty("use.selenium.grid"));
  }

  public String getBrowser() {
    return environment.getProperty("browser").toUpperCase();
  }

  public String getSeleniumGridEndpoint() {
    return environment.getProperty("selenium.grid.endpoint");
  }

  public String getPlatform() {
    return environment.getProperty("platform").toUpperCase();
  }

  public LanguageType getLanguage() {
    return LanguageType.valueOf(environment.getProperty("language").toUpperCase());
  }

}
