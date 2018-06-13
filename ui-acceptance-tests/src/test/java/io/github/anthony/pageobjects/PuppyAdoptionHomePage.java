package io.github.anthony.pageobjects;

import io.github.anthony.common.enums.Puppy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public class PuppyAdoptionHomePage extends AbstractBasePage {

    private String pageUrl;

    @FindBy(xpath = "//form[@action=\"/puppies/4\"]//input")
    private WebElement viewDetailsOnBrook;
    @FindBy(xpath = "//form[@action=\"/puppies/3\"]//input")
    private WebElement viewDetailsOnHannah;

    @Autowired
    public PuppyAdoptionHomePage(WebDriver webdriver, WebDriverWait explicitWait, String pageUrl) {
        super(webdriver, explicitWait);
        this.pageUrl = pageUrl;
    }

    public PuppyAdoptionHomePage open() {
        getWebdriver().get(pageUrl);
        return this;
    }

    public void adoptADog(Puppy name) {
        switch(name) {
            case BROOK:
                viewDetailsOnBrook.click();
                break;
            case HANNA:
                break;
            case MAGGIE_MAE:
                break;
            case RUBY_SUE:
                break;
        }
    }
}
