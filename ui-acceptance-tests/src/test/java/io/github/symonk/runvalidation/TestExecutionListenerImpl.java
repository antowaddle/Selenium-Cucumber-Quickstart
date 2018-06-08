package io.github.symonk.runvalidation;

import lombok.extern.slf4j.Slf4j;
import org.testng.IExecutionListener;

@Slf4j
public class TestExecutionListenerImpl implements IExecutionListener {


    @Override
    public void onExecutionStart() {
        log.info("Execution started...");
    }

    @Override
    public void onExecutionFinish() {
        log.info("Execution finished...");
    }
}
