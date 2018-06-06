package io.github.symonk.spring;

import io.github.symonk.webdriver.Driver;
import org.springframework.context.annotation.Bean;

public class SpringConfiguration {

    @Bean
    public Driver driver() {
        return new Driver();
    }

}
