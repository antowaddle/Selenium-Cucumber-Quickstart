package io.github.symonk.pageobjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AbstractBasePage {

  private WebDriver webdriver;
  private WebDriverWait explicitWait;

  @Autowired
  public AbstractBasePage(WebDriver webdriver, WebDriverWait explicitWait) {
    this.webdriver = webdriver;
    this.explicitWait = explicitWait;
  }

  public void navigateToPageAndWait(String url) {
    webdriver.get(url);
  }

  public void setElementTextValue(WebElement elementToSetTextOn, String text) {
    elementToSetTextOn.clear();
    elementToSetTextOn.sendKeys(text);
  }
}
