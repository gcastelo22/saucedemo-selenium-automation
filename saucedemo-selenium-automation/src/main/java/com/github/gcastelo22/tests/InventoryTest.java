package com.github.gcastelo22.tests;

import com.github.gcastelo22.core.BaseTest;
import com.github.gcastelo22.pages.LoginPage;
import org.junit.Test;

public class InventoryTest extends BaseTest {

  @Test
  public void shouldAddBackpackToCartSuccessfully() {
    new LoginPage(driver)
        .loginAsStandardUser()
        .verifyPageTitle("Products")
        .addProductToCart("Sauce Labs Backpack")
        .verifyCartBadgeCount("1")
        .goToCart()
        .verifyPageTitle("Your Cart")
        .verifyProductInCart("Sauce Labs Backpack");
  }
}