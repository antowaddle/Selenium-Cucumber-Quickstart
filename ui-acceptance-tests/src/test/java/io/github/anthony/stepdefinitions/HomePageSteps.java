package io.github.anthony.stepdefinitions;

import cucumber.api.java.en.Given;
import io.github.anthony.pageobjects.PuppyAdoptionHomePage;
import org.springframework.beans.factory.annotation.Autowired;

public class HomePageSteps extends AbstractBaseStepDef {

    private PuppyAdoptionHomePage puppyAdoptionHomePage;

    @Autowired
    public HomePageSteps(PuppyAdoptionHomePage puppyAdoptionHomePage) {
        this.puppyAdoptionHomePage = puppyAdoptionHomePage;
    }

    @Given("^the puppy adoption page has been loaded$")
    public void the_puppy_adoption_page_has_been_loaded() {
        puppyAdoptionHomePage.open();
    }

}
