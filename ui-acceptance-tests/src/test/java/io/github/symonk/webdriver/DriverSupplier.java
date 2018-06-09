package io.github.symonk.webdriver;

import io.github.symonk.spring.AutomationProperties;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class DriverSupplier {

    private final boolean useSeleniumGrid;
    private final String browser;
    private final String seleniumGridEndpoint;
    private final String platform;


    @Autowired
    public DriverSupplier(AutomationProperties properties) {
        this.useSeleniumGrid = properties.getRunOnSeleniumGrid();
        this.browser = properties.getBrowser();
        this.seleniumGridEndpoint = properties.getSeleniumGridEndpoint();
        this.platform = properties.getPlatform();
    }

    public RemoteWebDriver instantiateASharedDriver() {
        log.info("Requesting a new driver instance...");
        if(useSeleniumGrid) log.info("RemoteWebDriver and selenium grid endpoint: " + seleniumGridEndpoint);
        if(!isDriverChrome() && !isDriverFirefox()) throw new InvalidArgumentException("Unsupported browser type specified at runtime");
        return buildDriver();
    }

    private RemoteWebDriver buildDriver() {
        MutableCapabilities capabilities = new DesiredCapabilities();
        DriverType typeOfBrowser = DriverType.valueOf(browser);
        RemoteWebDriver temp;

        if(useSeleniumGrid)
            if(isDriverChrome()) return buildDistributedChromeDriver();
            if(isDriverFirefox()) return buildDistributedFirefoxDriver();


        temp = typeOfBrowser.getWebDriver(capabilities);
        if(null != temp && isDriverFirefox()) temp.manage().window().maximize();
        return temp;
    }

    private URL prepareGridUrl() {
        URL temp;
        try {
            temp = new URL(seleniumGridEndpoint);
        } catch (MalformedURLException exception) {
            throw new InvalidArgumentException("Specified grid endpoint was not valid: " + seleniumGridEndpoint);
        }

        return temp;
    }

    private RemoteWebDriver buildDistributedChromeDriver() {
        ChromeOptions options = DriverType.getChromeCapabilities();
        options.setCapability("platformName", platform);
      return new RemoteWebDriver(prepareGridUrl(), options);
    }

    private RemoteWebDriver buildDistributedFirefoxDriver() {
        FirefoxOptions options = DriverType.getFirefoxCapabilities();
        options.setCapability("platformName", platform);
        return new RemoteWebDriver(prepareGridUrl(), options);
    }
    private boolean isDriverFirefox() {
        return browser.equals("FIREFOX");
    }

    private boolean isDriverChrome() {
        return browser.equals("CHROME");
    }
}
