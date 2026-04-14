# SauceDemo Selenium Automation Framework

A professional-grade E2E (End-to-End) test automation framework built with **Java 21**, **Selenium WebDriver** and **JUnit 4**, following the best practices of 2026.

## 🚀 Overview

This project automates the critical user journeys of the [SauceDemo](https://www.saucedemo.com/) e-commerce platform. It was designed with scalability, maintainability and stability in mind, featuring a robust Page Object Model (POM) architecture.

## 🏗️ Architecture & Design Patterns

- **Page Object Model (POM):** Each web page is represented as a class, encapsulating locators and behaviors.
- **Fluent Interface:** Methods return page objects to allow for "method chaining", making tests readable and expressive.
- **Base Architecture:** Centralized "BaseTest" for driver lifecycle and "BasePage" for common actions and synchronization.
- **Synchronization:** Strictly uses **Explicit Waits** to handle asynchronous elements, eliminating flaky "Thread.sleep" calls.
- **Configuration Management:** Powered by **Typesafe Config** (HOCON), allowing easy environment switching without touching the code.

## 🛠️ Tech Stack

* **Language:** Java 21
* **Automation:** Selenium WebDriver (4.x)
* **Testing Framework:** JUnit 4
* **Build Tool:** Maven
* **Reporting:** Automatic Screenshots on every test run (stored locally).
* **Utilities:** Commons-IO, Typesafe Config.

## 📋 Prerequisites

- **JDK 21** or higher.
- **Maven** installed and configured in your PATH.
- **Google Chrome** (The framework uses Selenium Manager to handle driver binaries automatically).

## ⚙️ Configuration

Before running tests, ensure your configuration is set in "src/main/resources/config/env.conf":

```hocon
conf {
    browser = "chrome"
    url = "[https://www.saucedemo.com/](https://www.saucedemo.com/)"
    timeout = 10
    headless = false
}
```

## 🏃 Running the Tests

The framework supports execution via terminal, which is ideal for CI/CD pipelines. You can run the entire suite or specific tests using Maven commands.

### Run All Tests
To execute all test suites in the project:
```bash
mvn clean test
```

### Run Specific Test Suites
To focus on a specific part of the application, you can run the following suites independently:

- **Complete E2E Purchase Flow:**
  ```bash
  mvn test -Dtest=E2EPurchaseTest
  ```
  
  - **Authentication & Login Suite:**
  ```bash
  mvn test -Dtest=LoginTest
  ```
  
  - **Inventory & Catalog Suite:**
  ```bash
  mvn test -Dtest=InventoryTest
  ```
  
## 📸 Evidence & Reporting

The framework is equipped with automatic evidence collection to facilitate debugging and local audit trails.

### Automatic Screenshots
- **Behavior:** A screenshot is captured automatically at the end of each test execution.
- **Storage:** Files are saved locally in the "target/screenshots/" directory.
- **Naming Convention:** Files are named after the test method name for easy identification (e.g., "shouldCompleteFullPurchaseFlow.png").
- **Note:** The "target/" directory is ignored by Git to keep the repository lightweight.

### Logging
- **Console Logs:** Detailed execution steps are logged using "java.util.logging", providing real-time feedback on browser initialization, navigation and element interactions.
- **Assertion Failures:** In case of failure, clear descriptive messages are provided in the console to pinpoint exactly which validation step failed.

## 📂 Project Structure

The project follows a modular Maven-based structure, where both framework logic and test suites are organized for clarity and easy navigation.

```text
src
├── main/java/com/github/gcastelo22
│   ├── core/           # Framework Engine
│   │   ├── BasePage.java       # Common actions and explicit waits
│   │   └── BaseTest.java       # Driver lifecycle and screenshot logic
│   ├── pages/          # Page Object classes (POM)
│   │   ├── LoginPage.java
│   │   ├── ProductsPage.java
│   │   ├── CartPage.java
│   │   ├── CheckoutInfoPage.java
│   │   └── CheckoutOverviewPage.java
│   └── tests/          # Automated Test Suites
│       ├── LoginTest.java      # Authentication scenarios
│       ├── InventoryTest.java  # Catalog and product validation
│       └── E2EPurchaseTest.java # Full business flow
└── main/resources/     # Configuration
    └── config/
        └── env.conf            # Browser and environment settings
```
		
---

**Developed by Guilherme Castelo**
*Senior Quality Engineer | SDET | Data Integrity Specialist*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guilhermecastelo/)
[![Email](https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:lguilherme.castelo@gmail.com)	