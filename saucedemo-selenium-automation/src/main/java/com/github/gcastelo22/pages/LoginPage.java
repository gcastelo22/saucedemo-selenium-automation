package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing the Login screen of Sauce Demo.
 * Extends BasePage to utilize common web interactions.
 */
public class LoginPage extends BasePage {

  // Locators
  private final By usernameField = By.id("user-name");
  private final By passwordField = By.id("password");
  private final By loginButton = By.id("login-button");
  private final By errorMessage = By.cssSelector("[data-test='error']");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Enters the username using the inherited "write" method.
   * @param user The username string.
   * @return Current instance for chaining.
   */
  public LoginPage enterUsername(String user) {
    write(usernameField, user);
    return this;
  }

  /**
   * Enters the password using the inherited "write" method.
   * @param pass The password string.
   * @return Current instance for chaining.
   */
  public LoginPage enterPassword(String pass) {
    write(passwordField, pass);
    return this;
  }

  /**
   * Clicks the login button and transitions to the ProductsPage.
   * @return A new instance of ProductsPage.
   */
  public ProductsPage clickLogin() {
    click(loginButton);
    return new ProductsPage(driver);
  }

  /**
   * High-level action to perform a complete login.
   * Demonstrates the power of the Fluent pattern.
   */
  public ProductsPage loginAsStandardUser() {
    return enterUsername("standard_user")
        .enterPassword("secret_sauce")
        .clickLogin();
  }

  /**
   * Retrieves the error message using the inherited "getText" method.
   */
  public String getErrorMessageText() {
    return getText(errorMessage);
  }
}