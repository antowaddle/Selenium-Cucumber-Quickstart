package io.github.anthony.runvalidation;

import io.github.anthony.spring.AutomationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/framework.properties"})
@Slf4j
public class TestExecutionSpringConfig {

  @Bean
  public AutomationProperties properties() {
    return new AutomationProperties();
  }

  @Bean
  public TestExecutionListener testExecutionListener() {
    return new TestExecutionListener();
  }
}
