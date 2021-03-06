package io.github.anthony.pageobjects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Slf4j
public class AbstractBasePage {

  @Autowired @Getter(AccessLevel.PROTECTED) private ApplicationContext applicationContext;
  @Getter(AccessLevel.PROTECTED) private WebDriver webdriver;
  @Getter(AccessLevel.PROTECTED) private WebDriverWait explicitWait;

  @Autowired
  public AbstractBasePage(WebDriver webdriver, WebDriverWait explicitWait) {
    this.webdriver = webdriver;
    this.explicitWait = explicitWait;
    PageFactory.initElements(webdriver, this);
  }

  public void navigateToPageAndWait(String url) {
    webdriver.get(url);
  }

  public void setElementTextValue(WebElement elementToSetTextOn, String text) {
    explicitWait.until(ExpectedConditions.elementToBeClickable(elementToSetTextOn));
    elementToSetTextOn.clear();
    elementToSetTextOn.sendKeys(text);
  }
}
