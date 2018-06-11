package io.github.symonk.stepdefinitions;

import io.github.symonk.cucumber.TestWorld;
import io.github.symonk.spring.AutomationProperties;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractBaseStepDef {

    @Autowired @Getter(AccessLevel.PROTECTED)  AutomationProperties properties;
    @Autowired @Getter(AccessLevel.PROTECTED) TestWorld testWorld;

}
