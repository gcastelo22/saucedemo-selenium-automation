package com.github.gcastelo22.tests;

import com.github.gcastelo22.core.BaseTest;
import com.github.gcastelo22.pages.LoginPage;
import org.junit.Test;

/**
 * Focuses on authentication scenarios.
 */
public class LoginTest extends BaseTest {

  @Test
  public void shouldLoginSuccessfully() {
    new LoginPage(driver)
        .enterUsername("standard_user")
        .enterPassword("secret_sauce")
        .clickLogin()
        .verifyPageTitle("Products");
  }

  @Test
  public void shouldShowErrorForLockedOutUser() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUsername("locked_out_user")
        .enterPassword("secret_sauce")
        .clickLogin();

    // Validation of error message (Logic added to LoginPage previously)
    org.junit.Assert.assertTrue(loginPage.getErrorMessageText().contains("Sorry, this user has been locked out"));
  }
}