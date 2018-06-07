package io.github.symonk.cucumber;

import cucumber.api.java.Before;
import io.github.symonk.spring.SpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@ContextConfiguration(classes = SpringConfiguration.class)
public class GeneralHooks {

    @Before
    public void recogniseSpringPlaceHolder() {

    }

}
