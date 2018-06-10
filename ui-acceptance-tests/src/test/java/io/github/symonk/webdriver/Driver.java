package io.github.symonk.webdriver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Slf4j
public class Driver extends EventFiringWebDriver {

  private final Thread JVM_SHUTDOWN_HOOK =
      new Thread(
          () -> {
            log.info("JVM is terminating, quitting the shared driver");
            quit();
          });

  @Autowired
  public Driver(DriverSupplier supplier) {
    super(supplier.instantiateASharedDriver());
    log.info("Registering shutdown hook to the driver instance");
    Runtime.getRuntime().addShutdownHook(JVM_SHUTDOWN_HOOK);
    log.info("registering event listener to the driver instance");
    register(new DriverListener());
  }

  @Override
  public void quit() {
    if (Thread.currentThread() != JVM_SHUTDOWN_HOOK)
      throw new UnsupportedOperationException("Quitting the driver is handled by the JVM shutdown hook");
    super.quit();
  }

  /**
   * As per W3C we are bounded by the domain we reside on, so clearing cookies on an another domain
   * will first require a .get() to that domain before clearing, we cannot modify such data from
   * outside the domain.
   */
  public void clearLocalStorage() {
    log.info("Clearing local storage");
    executeScript("localStorage.clear();");
  }

  public void clearSessionStorage() {
    log.info("Clearing session storage");
    executeScript("sessionStorage.clear();");
  }

  public void clearCookies() {
    log.info("Clearing cookies");
    manage().deleteAllCookies();
  }

  public void closeAllSpawnedBrowserTabs(String tabToKeep) {
    Set<String> openHandles = getWindowHandles();
    if(openHandles.size() == 1) throw new InvalidArgumentException("cannot close the last remaining tab: " + tabToKeep);
    openHandles.forEach(tab -> {
      if (!tab.equals(tabToKeep)) {
        log.info("Not parent tab found, switching and closing it");
        switchTo().window(tab).close();
      }
    });

    log.info("Switching control back to the parent tab");
    switchTo().window(tabToKeep).close();
  }

}
