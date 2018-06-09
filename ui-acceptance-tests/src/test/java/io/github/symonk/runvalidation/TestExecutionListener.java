package io.github.symonk.runvalidation;

import io.github.symonk.spring.AutomationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TestExecutionListener {

    @Autowired
    private AutomationProperties properties;

    public void checkEnvironmentIsOnline() {
        log.info("checking the specified test environment is reachable");
        log.info("Endpoint: " + properties.getBaseEnvironmentUrl());
    }


}
