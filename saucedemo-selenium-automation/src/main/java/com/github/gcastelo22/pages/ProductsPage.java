package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

/**
 * Page Object representing the Inventory screen.
 * Implements Fluent Interface for seamless navigation.
 */
public class ProductsPage extends BasePage {

  private final By titleSpan = By.className("title");
  private final By shoppingCartBadge = By.className("shopping_cart_badge");
  private final By cartLink = By.className("shopping_cart_link");

  private By addToCartButton(String productName) {
    String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
    return By.id(buttonId);
  }

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  public ProductsPage verifyPageTitle(String expectedTitle) {
    String actualTitle = getText(titleSpan);
    Assert.assertEquals("Page title mismatch!", expectedTitle, actualTitle);
    return this;
  }

  public ProductsPage addProductToCart(String productName) {
    click(addToCartButton(productName));
    return this;
  }

  public ProductsPage verifyCartBadgeCount(String expectedCount) {
    String actualCount = getText(shoppingCartBadge);
    Assert.assertEquals("Cart badge count mismatch!", expectedCount, actualCount);
    return this;
  }

  /**
   * Navigates to the Cart page and waits for the URL transition
   * to prevent synchronization errors (ComparisonFailure).
   */
  public CartPage goToCart() {
    LOG.info("Navigating to the shopping cart.");
    click(cartLink);

    // Wait for the URL to change to the cart page before returning the new object
    wait.until(ExpectedConditions.urlContains("cart.html"));

    return new CartPage(driver);
  }
}