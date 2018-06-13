package io.github.anthony.runvalidation;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Slf4j
@ContextConfiguration(classes = TestExecutionSpringConfig.class)
public class TestExecutionValidator extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestExecutionListener testExecutionListener;
    private final String downloadDirectory = "src/test/resources/binaries";

    @Test
    public void validateRunConditionsHere() {
        log.info("Test run instantiated...validating conditions");
        testExecutionListener.checkEnvironmentIsOnline();
        WebDriverManager.chromedriver().targetPath(downloadDirectory).setup();
        WebDriverManager.firefoxdriver().targetPath(downloadDirectory).setup();
    }

}
