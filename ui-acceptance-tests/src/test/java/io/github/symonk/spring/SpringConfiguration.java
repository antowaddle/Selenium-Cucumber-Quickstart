package io.github.symonk.spring;

import io.github.symonk.helpers.logging.LogHelper;
import io.github.symonk.helpers.logging.Loggable;
import io.github.symonk.helpers.slack.SlackHelper;
import io.github.symonk.helpers.slack.Slackable;
import io.github.symonk.runvalidation.TestExecutionListener;
import io.github.symonk.webdriver.Driver;
import lombok.extern.slf4j.Slf4j;
import net.gpedro.integrations.slack.SlackApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource(value = {"classpath:/framework.properties"})
public class SpringConfiguration {

  @Bean
  public TestExecutionListener testExecutionListener() {
    return new TestExecutionListener();
  }

  @Bean
  public Driver driver() {
    return new Driver();
  }

  @Bean
  public AutomationProperties properties() {
    return new AutomationProperties();
  }

  @Bean
  public SlackApi slackHelper() {
    return new SlackApi(properties().getSlackApiToken());
  }

  @Bean
  public Loggable logHelper() {
    return new LogHelper();
  }
}
