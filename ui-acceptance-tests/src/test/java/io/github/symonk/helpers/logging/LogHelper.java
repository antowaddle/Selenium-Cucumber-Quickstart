package io.github.symonk.helpers.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class LogHelper implements Loggable {

  private final String testName = "scenario_name";

  @Override
  public void startLogging(String name) {
    log.info("Starting test logger... " + name);
    MDC.put(testName, name);
  }

  @Override
  public String stopLogging() {
    String name = MDC.get(testName);
    MDC.remove(testName);
    log.info("Stopping test logger... " + name);
    return name;
  }
}
