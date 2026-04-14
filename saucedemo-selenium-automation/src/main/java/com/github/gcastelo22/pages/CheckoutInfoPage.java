package com.github.gcastelo22.pages;

import com.github.gcastelo22.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the first step of Checkout (User Information).
 */
public class CheckoutInfoPage extends BasePage {

  private final By firstNameField = By.id("first-name");
  private final By lastNameField = By.id("last-name");
  private final By zipCodeField = By.id("postal-code");
  private final By continueButton = By.id("continue");

  public CheckoutInfoPage(WebDriver driver) {
    super(driver);
  }

  /**
   * Fills the user details and proceeds to the overview.
   * @return A new instance of CheckoutOverviewPage.
   */
  public CheckoutOverviewPage fillInformation(String first, String last, String zip) {
    write(firstNameField, first);
    write(lastNameField, last);
    write(zipCodeField, zip);
    click(continueButton);
    return new CheckoutOverviewPage(driver);
  }
}