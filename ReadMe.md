# **Automated Testing Framework using Cucumber, Selenium, and TestNG**

## **Overview**
This project is an automated testing framework built using **Cucumber**, **Selenium WebDriver**, and **TestNG**. The framework is designed to test an e-commerce website (**DemoBlaze**) and includes scenarios for login, adding products to the cart, and purchasing items with expired or invalid credit cards.

## **Project Structure**
|-- src/main/java
|   |-- Steps/
|   |   |-- Hooks.java
|   |   |-- LoginSteps.java
|   |   |-- PurchaseExpiredCardSteps.java
|   |   |-- Add_Product_Twice_to_Cart.java
|   |-- Utils/
|   |   |-- ScreenshotUtil.java
|-- src/test/resources
|   |-- Features/
|   |   |-- PurchaseExpiredCard.feature
|   |   |-- AddToCart.feature
|-- pom.xml
|-- README.md


## **Technologies Used**
- **Java** (Programming Language)
- **Selenium WebDriver** (Browser Automation)
- **Cucumber** (BDD Testing Framework)
- **TestNG** (Test Execution & Reporting)
- **Maven** (Dependency Management)

## **Setup and Installation**
### **Prerequisites**
1. Install **Java JDK (8 or higher)**
2. Install **Maven**
3. Install a **web browser (Chrome or Edge)**
4. Download the required **WebDriver** (Chromedriver or Edgedriver)

### **How to Set Up the Project**
1. Clone the repository:

bash
git clone https://github.com/your-repo-url.git

2. Navigate to the project directory:

bash
cd your-project-folder

3. Install dependencies:

bash
mvn clean install


## **How to Run Tests**
### **Running All Tests**
To execute all test scenarios, run:
bash
mvn test


### **Running Specific Feature Files**
To run a specific feature file, use:
bash
mvn test -Dcucumber.options="src/test/resources/Features/PurchaseExpiredCard.feature"


## **Key Components**
### **1. Hooks.java**
This class is responsible for **browser setup and teardown**.
java
@Before
public void setup() {
System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
driver = new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
}

@After
public void tearDown() {
if (driver != null) {
driver.quit();
}
}


### **2. LoginSteps.java**
This class handles the login functionality using Cucumber step definitions.
java
@Given("I am logged in Successfully")
public void i_am_logged_in_successfully() {
driver.get("https://www.demoblaze.com");
driver.findElement(By.id("login2")).click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));
driver.findElement(By.id("loginusername")).sendKeys("salaaah");
driver.findElement(By.id("loginpassword")).sendKeys("151515");
driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
}


### **3. ScreenshotUtil.java**
This utility class captures screenshots when tests fail.
java
@Attachment(value = "{0}", type = "image/png")
public static byte[] takeScreenshot(WebDriver driver, String screenshotName) {
return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
}


## **Reporting**
- Test execution reports can be generated using TestNG and Allure reports.
- To generate an Allure report, run:

bash
mvn allure:serve


## **Future Enhancements**
- Implement **API Testing** for backend validation.
- Extend test coverage for **different user roles**.
- Integrate with **CI/CD pipeline** for automated execution.

## **Contributors**
- **[ِِAntonious Magdy ]** - Automation Engineer

## **License**
This project is licensed under the MIT License.