package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

/**
 * Page Object for the Checkout Overview (Final Review).
 */
public class CheckoutOverviewPage extends BasePage {

  private final By finishButton = By.id("finish");
  private final By completeHeader = By.className("complete-header");

  public CheckoutOverviewPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Completes the order.
   * @return this instance to allow final verification.
   */
  public CheckoutOverviewPage clickFinish() {
    click(finishButton);
    LOG.info("Order finished.");
    return this;
  }

  /**
   * Final assertion to confirm the order was successful.
   */
  public void verifyOrderCompletion(String expectedMessage) {
    String actualMessage = getText(completeHeader);
    Assert.assertEquals("Order completion message mismatch!", expectedMessage, actualMessage);
  }
}