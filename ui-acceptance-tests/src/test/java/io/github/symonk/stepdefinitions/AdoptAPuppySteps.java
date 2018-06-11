package io.github.symonk.stepdefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.github.symonk.common.enums.Puppy;
import io.github.symonk.pageobjects.PuppyAdoptionHomePage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdoptAPuppySteps extends AbstractBaseStepDef {

    private PuppyAdoptionHomePage puppyAdoptionHomePage;

    @Autowired
    public AdoptAPuppySteps(PuppyAdoptionHomePage puppyAdoptionHomePage) {
        this.puppyAdoptionHomePage = puppyAdoptionHomePage;
    }

    @Given("^the puppy adoption page has been loaded$")
    public void the_puppy_adoption_page_has_been_loaded() {
        puppyAdoptionHomePage.open();
    }

    @When("^(.+) is adopted with the following options:$")
    public void a_dog_is_adopted_with_the_following_options(Puppy puppy, DataTable arg1) {
        puppyAdoptionHomePage.adoptADog(puppy);
    }


}
