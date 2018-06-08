package io.github.symonk.stepdefinitions;

import io.github.symonk.spring.AutomationProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractBaseStepDef {

    @Autowired protected AutomationProperties properties;

}
