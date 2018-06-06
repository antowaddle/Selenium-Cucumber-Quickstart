package io.github.symonk.runvalidation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestExecutionListener {

    public void checkEnvironmentIsOnline() {
        log.info("checking the specified test environment is reachable");
    }


}
