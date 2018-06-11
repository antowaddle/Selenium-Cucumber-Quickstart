package io.github.symonk.cucumber;

import cucumber.api.java.After;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;

public class CleanupHooks {

    private TestWorld testWorld;

    @Autowired
    public CleanupHooks(TestWorld testWorld) {
        this.testWorld = testWorld;
    }

    @After()
    @Step("Cleaning up test world to prevent leakage")
    public void cleanWorld() {
        testWorld.cleanUpWorld();
    }


}
