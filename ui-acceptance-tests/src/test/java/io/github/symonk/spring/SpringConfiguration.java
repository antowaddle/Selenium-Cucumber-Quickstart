package io.github.symonk.spring;

import io.github.symonk.runvalidation.TestExecutionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SpringConfiguration {

    @Bean
    public TestExecutionListener testExecutionListener() {
        return new TestExecutionListener();
    }


}
