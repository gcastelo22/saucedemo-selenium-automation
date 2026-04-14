package com.github.gcastelo22.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * BasePage serves as a parent for all Page Objects.
 * Centralizes common WebDriver actions and explicit waits.
 */
public abstract class BasePage extends BaseTest {

  protected WebDriver driver;
  protected WebDriverWait wait;
  protected static final Logger LOG = Logger.getLogger(BasePage.class.getName());

  public BasePage(WebDriver driver) {
    this.driver = driver;
    int timeoutInSeconds = APP_CONFIG.getInt("conf.timeout");
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
  }

  protected WebElement waitForElementVisible(By locator) {
    LOG.info("Waiting for visibility of element: " + locator);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected void click(By locator) {
    LOG.info("Clicking on element: " + locator);
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
  }

  protected void write(By locator, String text) {
    WebElement element = waitForElementVisible(locator);
    element.clear();
    LOG.info("Writing text into element: " + locator);
    element.sendKeys(text);
  }

  /**
   * Enhanced getText with a wait for non-empty content to avoid race conditions.
   */
  protected String getText(By locator) {
    wait.until(d -> d.findElement(locator).getText().length() > 0);
    String text = waitForElementVisible(locator).getText();
    LOG.info("Text retrieved: " + text);
    return text;
  }

  protected void scrollToElement(By locator) {
    WebElement element = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    LOG.info("Scrolled to element: " + locator);
  }

  protected boolean isElementPresent(By locator) {
    try {
      return !driver.findElements(locator).isEmpty();
    } catch (Exception e) {
      LOG.warning("Element not present: " + locator);
      return false;
    }
  }
}