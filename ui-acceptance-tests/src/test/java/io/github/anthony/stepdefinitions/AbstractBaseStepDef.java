package io.github.anthony.stepdefinitions;

import io.github.anthony.cucumber.TestWorld;
import io.github.anthony.spring.AutomationProperties;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractBaseStepDef {

    @Autowired @Getter(AccessLevel.PROTECTED)  AutomationProperties properties;
    @Autowired @Getter(AccessLevel.PROTECTED) TestWorld testWorld;

}
