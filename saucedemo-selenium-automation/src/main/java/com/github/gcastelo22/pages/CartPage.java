package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

/**
 * Page Object representing the Shopping Cart screen.
 */
public class CartPage extends BasePage {

  private final By titleSpan = By.className("title");
  private final By checkoutButton = By.id("checkout");

  private By productByName(String productName) {
    return By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");
  }

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public CartPage verifyPageTitle(String expectedTitle) {
    Assert.assertEquals("Cart page title mismatch!", expectedTitle, getText(titleSpan));
    return this;
  }

  public CartPage verifyProductInCart(String productName) {
    LOG.info("Verifying product in cart: " + productName);
    Assert.assertTrue("Product " + productName + " was not found!", isElementPresent(productByName(productName)));
    return this;
  }

  /**
   * Clicks checkout and transitions to the Information step.
   * @return A new instance of CheckoutInfoPage.
   */
  public CheckoutInfoPage clickCheckout() {
    click(checkoutButton);
    LOG.info("Proceeding to checkout information.");
    return new CheckoutInfoPage(driver);
  }
}