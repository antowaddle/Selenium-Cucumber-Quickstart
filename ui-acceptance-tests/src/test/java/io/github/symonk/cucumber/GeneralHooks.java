package io.github.symonk.cucumber;

import cucumber.api.java.After;
import io.github.symonk.spring.SpringConfiguration;
import io.github.symonk.webdriver.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@ContextConfiguration(classes = SpringConfiguration.class)
public class GeneralHooks {

    @After
    public void recogniseSpringPlaceHolder() {
        DriverFactory.getInstance().getDriver().quit();
    }

}
