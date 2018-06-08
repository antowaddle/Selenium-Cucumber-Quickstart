package io.github.symonk.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class AutomationProperties {

  @Autowired private Environment environment;

  public String getBaseEnvironmentUrl() {
    return environment.getProperty("application.baseurl");
  }

  public String getSlackApiToken() {
    return environment.getProperty("slack.api.token");
  }
}
