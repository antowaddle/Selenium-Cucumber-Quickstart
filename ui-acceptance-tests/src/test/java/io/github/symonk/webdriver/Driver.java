package io.github.symonk.webdriver;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.helper.StringUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Set;

@Slf4j
public class Driver extends EventFiringWebDriver {

    private static WebDriver WRAPPED_WEBDRIVER = DriverFactory.getInstance().getDriver();

    public Driver() {
        super(WRAPPED_WEBDRIVER);
        log.info("registering event listener to the driver instance");
        this.register(new DriverListener());
    }

    public void clearSessionStorageAndLocalStorage() {
        log.info("Clearing local storage");
        this.executeScript("localStorage.clear();");
        log.info("Clearing session storage");
        this.executeScript("sessionStorage.clear();");
    }

    public void closeAllNonParentTabs(String parentTab) {
        Set<String> handles = this.getWindowHandles();
        if (StringUtil.isBlank(parentTab) || handles.size() == 1) {
            log.error(
                    "invalid window handle was passed or you tried to manually close the last remaining browser tab");
            log.error("not attempting any tab interactions, terminating the method...");
            return;
        }
        handles.forEach(
                (handleName) -> {
                    if (!handleName.equals(parentTab)) {
                        log.info("found a non parent tab, closing it");
                        this.switchTo().window(handleName).close();
                    }
                });
        log.info("finished tab scanning... switch context to the main parent tab");
        this.switchTo().window(parentTab);
    }
}
