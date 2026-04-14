package com.github.gcastelo22.tests;

import com.github.gcastelo22.core.BaseTest;
import com.github.gcastelo22.pages.LoginPage;
import org.junit.Test;

/**
 * End-to-End Test Suite for the Sauce Demo purchase journey.
 * This test simulates a complete user story: Login, adding a product,
 * reviewing the cart and completing the checkout process.
 */
public class E2EPurchaseTest extends BaseTest {

  @Test
  public void shouldCompleteFullPurchaseFlow() {
    // Test Data
    String productName = "Sauce Labs Backpack";
    String firstName = "Guilherme";
    String lastName = "Castelo";
    String zipCode = "1234-567";
    String successMessage = "Thank you for your order!";

    // Fluent Flow execution
    new LoginPage(driver)
        .loginAsStandardUser()
        .verifyPageTitle("Products")
        .addProductToCart(productName)
        .verifyCartBadgeCount("1")
        .goToCart()
        .verifyPageTitle("Your Cart")
        .verifyProductInCart(productName)
        .clickCheckout()
        .fillInformation(firstName, lastName, zipCode)
        .clickFinish()
        .verifyOrderCompletion(successMessage);

    LOG.info("E2E Purchase Flow completed successfully for: " + productName);
  }
}