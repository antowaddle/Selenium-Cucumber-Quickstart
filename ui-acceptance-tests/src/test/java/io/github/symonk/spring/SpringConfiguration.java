package io.github.symonk.spring;

import io.github.symonk.runvalidation.TestExecutionListener;
import io.github.symonk.webdriver.Driver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SpringConfiguration {

    @Bean
    public TestExecutionListener testExecutionListener() {
        return new TestExecutionListener();
    }

    @Bean
    public Driver driver() {
        return new Driver(new ChromeDriver());
    }


}
