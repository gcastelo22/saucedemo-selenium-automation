package com.github.gcastelo22.core;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * BaseTest handles the WebDriver lifecycle, configuration loading and test evidence.
 * All Test Suites must extend this class to inherit the automation engine.
 */
public abstract class BaseTest {

  protected WebDriver driver;
  protected static final Logger LOG = Logger.getLogger(BaseTest.class.getName());

  // Load environment configurations from HOCON file
  protected static final Config APP_CONFIG = ConfigFactory.load("config/env.conf");
  protected static final String BROWSER = APP_CONFIG.getString("conf.browser");
  protected static final String URL = APP_CONFIG.getString("conf.url");
  protected static final boolean HEADLESS = APP_CONFIG.getBoolean("conf.headless");

  /**
   * JUnit Rule to retrieve the current test method name for file naming.
   */
  @Rule
  public TestName testName = new TestName();

  /**
   * Setup method executed before each test.
   * Initializes the driver, maximizes the window and navigates to the base URL.
   */
  @Before
  public void setUp() {
    LOG.info("Initializing Browser: " + BROWSER);
    initializeDriver();
    driver.manage().window().maximize();
    LOG.info("Navigating to: " + URL);
    driver.get(URL);
  }

  /**
   * Configures the specific WebDriver instance based on the env.conf settings.
   * Includes ChromeOptions to suppress Google Password Manager and system pop-ups.
   */
  private void initializeDriver() {
    switch (BROWSER.toUpperCase()) {
      case "CHROME" -> {
        ChromeOptions options = new ChromeOptions();

        // Preferences to disable the "Save Password" prompt and Password Manager
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Stability arguments
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");

        if (HEADLESS) options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
      }
      case "FIREFOX" -> {
        FirefoxOptions options = new FirefoxOptions();
        if (HEADLESS) options.addArguments("-headless");
        driver = new FirefoxDriver(options);
      }
      case "EDGE" -> {
        EdgeOptions options = new EdgeOptions();
        if (HEADLESS) options.addArguments("--headless");
        driver = new EdgeDriver(options);
      }
      default -> throw new IllegalArgumentException("Browser not supported: " + BROWSER);
    }
  }

  /**
   * Teardown method executed after each test.
   * Captures a screenshot of the final state and closes the browser session.
   */
  @After
  public void tearDown() {
    if (driver != null) {
      takeScreenshot();
      LOG.info("Closing browser session...");
      driver.quit();
    }
  }

  /**
   * Captures a screenshot and saves it to the target/screenshots directory.
   * The filename is based on the current test method name.
   */
  private void takeScreenshot() {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String path = "target/screenshots/" + testName.getMethodName() + ".png";
    try {
      FileUtils.copyFile(scrFile, new File(path));
      LOG.info("Screenshot successfully saved to: " + path);
    } catch (IOException e) {
      LOG.warning("Failed to save screenshot: " + e.getMessage());
    }
  }
}