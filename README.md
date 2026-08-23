<<<<<<< Updated upstream
Enterprise Automation Framework

A reusable UI automation framework built with Java 21, Selenium 4, TestNG, Cucumber BDD, Maven, Log4j2, Apache POI, and Allure.

The framework supports Chrome, Firefox, and Edge; headless/private execution; Page Object Model; Excel data-driven testing; secure credentials; TestNG and Cucumber; parallel-safe WebDriver using ThreadLocal; logging; screenshots on failure; and Allure reporting.

Technology Stack

Component

Technology

Language

Java 21

UI Automation

Selenium 4

Test Runner

TestNG

BDD

Cucumber

Build Tool

Maven

Driver Management

WebDriverManager

Test Data

Apache POI / Excel

Logging

Log4j2

Reporting

Allure

Design

Page Object Model + PageFactory

Parallel Execution

TestNG + ThreadLocal WebDriver

Framework Architecture

=======
# Enterprise Automation Framework

A reusable UI automation framework built with **Java 21**, **Selenium 4**, **TestNG**, **Cucumber BDD**, **Maven**, **Log4j2**, **Apache POI**, and **Allure**.

The framework supports Chrome, Firefox, and Edge; headless/private execution; Page Object Model; Excel data-driven testing; secure credentials; TestNG and Cucumber; parallel-safe WebDriver using `ThreadLocal`; logging; screenshots on failure; and Allure reporting.

---

## Technology Stack

| Component | Technology |
|---|---|
| Language | Java 21 |
| UI Automation | Selenium 4 |
| Test Runner | TestNG |
| BDD | Cucumber |
| Build Tool | Maven |
| Driver Management | WebDriverManager |
| Test Data | Apache POI / Excel |
| Logging | Log4j2 |
| Reporting | Allure |
| Design | Page Object Model + PageFactory |
| Parallel Execution | TestNG + ThreadLocal WebDriver |

---

## Framework Architecture

```mermaid
>>>>>>> Stashed changes
flowchart TD
    A[Test Execution] --> B{Execution Type}
    B --> C[TestNG]
    B --> D[Cucumber]

    C --> E[BaseTest]
    D --> F[CucumberHooks]

    E --> G[DriverService]
    F --> G

    G --> H[DriverManager]
    H --> I[ThreadLocal WebDriver]

    G --> J[BrowserFactory]
    J --> K[ChromeDriver]
    J --> L[FirefoxDriver]
    J --> M[EdgeDriver]

    C --> N[Test Classes]
    D --> O[Step Definitions]

    N --> P[Page Objects]
    O --> P

    P --> Q[BasePage]
    Q --> I

    R[ConfigReader] --> G
    S[BrowserConfig] --> J
    T[CredentialProvider] --> N
    T --> O

    U[ExcelReader] --> V[DataProviders]
    V --> N

    W[TestListener] --> X[ScreenshotUtil]
    F --> X

    W --> Y[Allure]
    F --> Y
<<<<<<< Updated upstream

High-level flow:

=======
```

High-level flow:

```text
>>>>>>> Stashed changes
TestNG Test / Cucumber Scenario
             |
             v
      BaseTest / Hooks
             |
             v
        DriverService
             |
             v
        DriverManager
      ThreadLocal<WebDriver>
             |
             v
        BrowserFactory
       /      |       \
   Chrome  Firefox    Edge
             |
             v
         Page Objects
             |
             v
          Selenium
<<<<<<< Updated upstream

Project Structure

=======
```

---

## Project Structure

```text
>>>>>>> Stashed changes
enterprise-automation-framework
|
|-- src
|   |-- main
|   |   |-- java/com/eaf
|   |   |   |-- base
|   |   |   |-- config
|   |   |   |-- driver
|   |   |   |-- factory
|   |   |   |-- listeners
|   |   |   |-- pages
|   |   |   `-- utils
|   |   `-- resources
|   |       |-- config.properties
|   |       `-- log4j2.xml
|   |
|   `-- test
|       |-- java/com/eaf
|       |   |-- hooks
|       |   |-- runners
|       |   |-- stepdefinitions
|       |   |-- testdata
|       |   `-- tests
|       `-- resources
|           |-- features
|           |   |-- login.feature
|           |   `-- employee.feature
|           |-- testdata
|           `-- allure.properties
|
|-- pom.xml
|-- testng.xml
`-- README.md
<<<<<<< Updated upstream

Core Components

BrowserFactory

Creates browser-specific drivers for:

chrome
firefox
edge
=======
```

---

## Core Components

### BrowserFactory

Creates browser-specific drivers for:

```text
chrome
firefox
edge
```
>>>>>>> Stashed changes

It applies framework settings such as headless mode, incognito/private mode, and notification disabling.

Example:

<<<<<<< Updated upstream
=======
```properties
>>>>>>> Stashed changes
browser=firefox
headless=false
incognito=true
disableNotifications=true
<<<<<<< Updated upstream

DriverManager

DriverManager owns the current thread's WebDriver using:

ThreadLocal<WebDriver>

This allows safe parallel execution:

Thread 1 -> Driver A
Thread 2 -> Driver B
Thread 3 -> Driver C

DriverService

DriverService owns the browser lifecycle:

=======
```

### DriverManager

`DriverManager` owns the current thread's WebDriver using:

```java
ThreadLocal<WebDriver>
```

This allows safe parallel execution:

```text
Thread 1 -> Driver A
Thread 2 -> Driver B
Thread 3 -> Driver C
```

### DriverService

`DriverService` owns the browser lifecycle:

```text
>>>>>>> Stashed changes
Read configuration
      |
Create BrowserConfig
      |
BrowserFactory
      |
DriverManager.setDriver()
      |
Browser setup
      |
Navigate to application
<<<<<<< Updated upstream

Both TestNG and Cucumber reuse this service.

BaseTest

BaseTest is the TestNG lifecycle adapter:

=======
```

Both TestNG and Cucumber reuse this service.

### BaseTest

`BaseTest` is the TestNG lifecycle adapter:

```text
>>>>>>> Stashed changes
@BeforeMethod
    |
DriverService.startDriver()

@Test
    |
Test execution

@AfterMethod
    |
DriverService.quitDriver()
<<<<<<< Updated upstream

CucumberHooks

Cucumber uses the same lifecycle:

=======
```

### CucumberHooks

Cucumber uses the same lifecycle:

```text
>>>>>>> Stashed changes
@Before
   |
DriverService.startDriver()

Scenario

@After
   |
Failure screenshot if required
   |
DriverService.quitDriver()
<<<<<<< Updated upstream

Page Object Model

Tests and Cucumber steps do not call Selenium locators directly.

=======
```

---

## Page Object Model

Tests and Cucumber steps do not call Selenium locators directly.

```text
>>>>>>> Stashed changes
LoginTest / LoginSteps
         |
         v
     LoginPage
         |
         v
      BasePage
         |
         v
      Selenium
<<<<<<< Updated upstream

BasePage contains reusable Selenium actions such as:

click()
enterText()
isDisplayed()

Application-specific synchronization remains in the relevant page class. For example, the employee form loader wait remains in EmployeePage.

Test Data Strategy

The framework uses Apache POI for Excel data.

=======
```

`BasePage` contains reusable Selenium actions such as:

```text
click()
enterText()
isDisplayed()
```

Application-specific synchronization remains in the relevant page class. For example, the employee form loader wait remains in `EmployeePage`.

---

## Test Data Strategy

The framework uses Apache POI for Excel data.

```text
>>>>>>> Stashed changes
Excel
  |
ExcelReader
  |
DataProvider
  |
POJO
  |
TestNG Test
<<<<<<< Updated upstream

Header-based lookup is preferred:

getCellData("Employee", row, "EmployeeId");

instead of fragile numeric mappings:

getCellData("Employee", row, 4);

The header normalization supports variations such as:

EmployeeId
Employee ID
employee id

Credential Setup

Valid credentials are not stored in Java, Excel, or config.properties.

The framework reads:

EAF_USERNAME
EAF_PASSWORD

through CredentialProvider.

Windows PowerShell - current session

$env:EAF_USERNAME="your_username"
$env:EAF_PASSWORD="your_password"

Windows - persistent

setx EAF_USERNAME "your_username"
setx EAF_PASSWORD "your_password"

Open a new terminal after using setx.

Linux / macOS

export EAF_USERNAME="your_username"
export EAF_PASSWORD="your_password"

Do not commit real credentials to Git or print them in logs/reports.

TestNG Execution

Run the complete TestNG suite:

mvn clean test

Run only Login tests:

mvn -Dtest=LoginTest test

Run only Employee tests:

mvn -Dtest=EmployeeTest test

Compile tests without browser execution:

mvn test-compile

Cucumber Execution

Run Cucumber only:

mvn -Dtest=CucumberTestRunner test

Current feature coverage includes:

Login functionality
Employee management

Cucumber reuses the same driver lifecycle, credentials, pages, logging, and reporting as TestNG.

Parallel Execution

TestNG

Configured in testng.xml:

<suite name="Automation Suite"
       parallel="methods"
       thread-count="2">

Example:

TestNG Thread 1 -> Login Test    -> Driver 1
TestNG Thread 2 -> Employee Test -> Driver 2

Cucumber

The Cucumber runner uses:

=======
```

Header-based lookup is preferred:

```java
getCellData("Employee", row, "EmployeeId");
```

instead of fragile numeric mappings:

```java
getCellData("Employee", row, 4);
```

The header normalization supports variations such as:

```text
EmployeeId
Employee ID
employee id
```

---

## Credential Setup

Valid credentials are not stored in Java, Excel, or `config.properties`.

The framework reads:

```text
EAF_USERNAME
EAF_PASSWORD
```

through `CredentialProvider`.

### Windows PowerShell - current session

```powershell
$env:EAF_USERNAME="your_username"
$env:EAF_PASSWORD="your_password"
```

### Windows - persistent

```powershell
setx EAF_USERNAME "your_username"
setx EAF_PASSWORD "your_password"
```

Open a new terminal after using `setx`.

### Linux / macOS

```bash
export EAF_USERNAME="your_username"
export EAF_PASSWORD="your_password"
```

Do not commit real credentials to Git or print them in logs/reports.

---

## TestNG Execution

Run the complete TestNG suite:

```bash
mvn clean test
```

Run only Login tests:

```bash
mvn -Dtest=LoginTest test
```

Run only Employee tests:

```bash
mvn -Dtest=EmployeeTest test
```

Compile tests without browser execution:

```bash
mvn test-compile
```

---

## Cucumber Execution

Run Cucumber only:

```bash
mvn -Dtest=CucumberTestRunner test
```

Current feature coverage includes:

```text
Login functionality
Employee management
```

Cucumber reuses the same driver lifecycle, credentials, pages, logging, and reporting as TestNG.

---

## Parallel Execution

### TestNG

Configured in `testng.xml`:

```xml
<suite name="Automation Suite"
       parallel="methods"
       thread-count="2">
```

Example:

```text
TestNG Thread 1 -> Login Test    -> Driver 1
TestNG Thread 2 -> Employee Test -> Driver 2
```

### Cucumber

The Cucumber runner uses:

```java
>>>>>>> Stashed changes
@Override
@DataProvider(parallel = true)
public Object[][] scenarios() {
    return super.scenarios();
}
<<<<<<< Updated upstream

Example:

TestNG-PoolService-1 -> Employee scenario -> Driver A
TestNG-PoolService-2 -> Login scenario    -> Driver B

ThreadLocal<WebDriver> keeps browser sessions isolated.

Logging

Configured through:

src/main/resources/log4j2.xml

Runtime logs are written to:

target/logs/automation.log

Typical entries:

=======
```

Example:

```text
TestNG-PoolService-1 -> Employee scenario -> Driver A
TestNG-PoolService-2 -> Login scenario    -> Driver B
```

`ThreadLocal<WebDriver>` keeps browser sessions isolated.

---

## Logging

Configured through:

```text
src/main/resources/log4j2.xml
```

Runtime logs are written to:

```text
target/logs/automation.log
```

Typical entries:

```text
>>>>>>> Stashed changes
Starting test environment setup
Browser initialized successfully
Starting test environment cleanup
Browser session closed
<<<<<<< Updated upstream

Secrets must never be logged.

Screenshot on Failure

TestNG

=======
```

Secrets must never be logged.

---

## Screenshot on Failure

### TestNG

```text
>>>>>>> Stashed changes
Test failure
   |
TestListener.onTestFailure()
   |
ScreenshotUtil
   |
Screenshot / Allure attachment
<<<<<<< Updated upstream

Cucumber

=======
```

### Cucumber

```text
>>>>>>> Stashed changes
Scenario failure
      |
@After Hook
      |
ScreenshotUtil.captureAsBytes()
      |
scenario.attach()
      |
Allure
<<<<<<< Updated upstream

Cucumber failure screenshots appear in Allure under the scenario teardown as Failure Screenshot.

Allure Reporting

Raw results are generated under:

target/allure-results
=======
```

Cucumber failure screenshots appear in Allure under the scenario teardown as **Failure Screenshot**.

---

## Allure Reporting

Raw results are generated under:

```text
target/allure-results
```
>>>>>>> Stashed changes

Do not use the JSON files as the human-readable report.

Run tests:

<<<<<<< Updated upstream
mvn clean test

Then serve the Allure dashboard:

mvn allure:serve

Current reporting setup:

allure-maven: 2.12.0
Allure report runtime: 2.37.0

Generate a static report:

mvn allure:report

Important: do not run mvn clean between test execution and report generation because it deletes target/allure-results.

Reporting flow:

=======
```bash
mvn clean test
```

Then serve the Allure dashboard:

```bash
mvn allure:serve
```

Current reporting setup:

```text
allure-maven: 2.12.0
Allure report runtime: 2.37.0
```

Generate a static report:

```bash
mvn allure:report
```

Important: do not run `mvn clean` between test execution and report generation because it deletes `target/allure-results`.

Reporting flow:

```text
>>>>>>> Stashed changes
TestNG Tests --------------------\
                                  \
                                   > target/allure-results
                                  /          |
Cucumber Scenarios -------------/           v
                                      mvn allure:serve
                                             |
                                             v
                                      Allure Dashboard
<<<<<<< Updated upstream

Typical Local Workflow

=======
```

---

## Typical Local Workflow

```powershell
>>>>>>> Stashed changes
$env:EAF_USERNAME="your_username"
$env:EAF_PASSWORD="your_password"

mvn clean test
mvn allure:serve
<<<<<<< Updated upstream

Cucumber only:

mvn -Dtest=CucumberTestRunner test
mvn allure:serve

Generated Artifacts

Generated runtime output should normally not be committed:

target/
.allure/

Typical output:

=======
```

Cucumber only:

```powershell
mvn -Dtest=CucumberTestRunner test
mvn allure:serve
```

---

## Generated Artifacts

Generated runtime output should normally not be committed:

```text
target/
.allure/
```

Typical output:

```text
>>>>>>> Stashed changes
target/logs
target/screenshots
target/allure-results
target/cucumber-reports
target/surefire-reports
target/site
<<<<<<< Updated upstream

Recommended .gitignore:

=======
```

Recommended `.gitignore`:

```gitignore
>>>>>>> Stashed changes
target/
.allure/
.idea/
*.iml
<<<<<<< Updated upstream

Framework Design Principles

Single Responsibility

=======
```

---

## Framework Design Principles

### Single Responsibility

```text
>>>>>>> Stashed changes
BrowserFactory -> browser creation
DriverManager  -> WebDriver ownership
DriverService  -> browser lifecycle
BasePage       -> reusable Selenium operations
Page Objects   -> page-specific behavior
ExcelReader    -> Excel access
DataProvider   -> test-data delivery
TestListener   -> TestNG lifecycle events
CucumberHooks  -> Cucumber lifecycle events
ScreenshotUtil -> screenshot capture
<<<<<<< Updated upstream

DRY

TestNG and Cucumber share the same driver, page, credential, screenshot, logging, and reporting infrastructure.

Separation of Concerns

Tests define verification scenarios. Page Objects define UI behavior. Infrastructure manages browser, data, reporting, logging, and configuration.

Parallel Safety

The framework uses ThreadLocal<WebDriver> instead of a shared static driver.

Security

Secrets are supplied externally using environment variables.

Interview-Level Framework Explanation

The framework supports both TestNG and Cucumber on top of the same Selenium infrastructure. Browser creation is handled by BrowserFactory, while DriverService manages browser startup and shutdown. DriverManager stores WebDriver using ThreadLocal so parallel tests do not share sessions. TestNG uses BaseTest and Cucumber uses Hooks as lifecycle adapters. Both reuse the same Page Objects, configuration, credentials, logging, screenshot, and reporting layers. Test data is handled with Apache POI, POJOs, and TestNG DataProviders. Failures are detected through listeners or hooks and screenshots are attached to Allure. TestNG methods and Cucumber scenarios can execute in parallel safely.

Current UI Capabilities

=======
```

### DRY

TestNG and Cucumber share the same driver, page, credential, screenshot, logging, and reporting infrastructure.

### Separation of Concerns

Tests define verification scenarios. Page Objects define UI behavior. Infrastructure manages browser, data, reporting, logging, and configuration.

### Parallel Safety

The framework uses `ThreadLocal<WebDriver>` instead of a shared static driver.

### Security

Secrets are supplied externally using environment variables.

---

## Interview-Level Framework Explanation

> The framework supports both TestNG and Cucumber on top of the same Selenium infrastructure. Browser creation is handled by BrowserFactory, while DriverService manages browser startup and shutdown. DriverManager stores WebDriver using ThreadLocal so parallel tests do not share sessions. TestNG uses BaseTest and Cucumber uses Hooks as lifecycle adapters. Both reuse the same Page Objects, configuration, credentials, logging, screenshot, and reporting layers. Test data is handled with Apache POI, POJOs, and TestNG DataProviders. Failures are detected through listeners or hooks and screenshots are attached to Allure. TestNG methods and Cucumber scenarios can execute in parallel safely.

---

## Current UI Capabilities

```text
>>>>>>> Stashed changes
Multi-browser execution             ✅
Chrome / Firefox / Edge             ✅
Headless execution                  ✅
Incognito / private mode            ✅
Config-driven execution             ✅
Secure credentials                  ✅
Page Object Model                   ✅
PageFactory                         ✅
Explicit synchronization            ✅
Excel data-driven testing           ✅
Header-based Excel access           ✅
POJO test data                      ✅
TestNG DataProvider                 ✅
TestNG                              ✅
Cucumber BDD                        ✅
Shared WebDriver lifecycle          ✅
ThreadLocal WebDriver               ✅
Parallel TestNG execution           ✅
Parallel Cucumber execution         ✅
Log4j2                              ✅
Failure screenshots                 ✅
Allure reporting                    ✅
Cucumber screenshot attachments     ✅
<<<<<<< Updated upstream

Next Phase
=======
```

---

## Next Phase
>>>>>>> Stashed changes

The next major phase is API automation.

Planned topics:

<<<<<<< Updated upstream
=======
```text
>>>>>>> Stashed changes
RestAssured
Request / Response Specifications
Serialization / Deserialization
POJOs
Schema Validation
Authentication / OAuth
TokenManager
Reusable API Clients
TestNG Integration
Allure Reporting
UI + API integrated scenarios
<<<<<<< Updated upstream

Author

Akshay Shinde
=======
```

---

## Author

**Akshay Shinde**
>>>>>>> Stashed changes

Enterprise Automation Framework learning and implementation project.
