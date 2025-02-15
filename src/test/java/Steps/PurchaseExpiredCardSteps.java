package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Epic("E-Commerce Tests")
@Feature("Purchase Products Feature")
public class PurchaseExpiredCardSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Step("Launch browser and navigate to the application")
    @Given("I am logged in and have products in the cart")
    public void i_am_logged_in_and_have_products_in_the_cart() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();

        driver.get("https://www.demoblaze.com");
        takeScreenshot("Opened Home Page");

        driver.findElement(By.id("login2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));

        driver.findElement(By.id("loginusername")).sendKeys("salaaah");
        driver.findElement(By.id("loginpassword")).sendKeys("151515");
        driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        takeScreenshot("Logged in successfully");

        driver.findElement(By.xpath("//a[text()='Laptops']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sony vaio i5')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        takeScreenshot("Added Product to the cart");
    }

    @Step("User proceeds to checkout with an expired credit card")
    @When("I attempt to purchase using an expired credit card")
    public void i_attempt_to_purchase_using_an_expired_credit_card() {
        driver.findElement(By.xpath("//a[text()='Cart']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody[@id='tbodyid']/tr")));
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));
        driver.findElement(By.id("name")).sendKeys("Antonious");
        driver.findElement(By.id("country")).sendKeys("Germany");
        driver.findElement(By.id("city")).sendKeys("Dortmund");
        driver.findElement(By.id("card")).sendKeys("1234567890123456"); // Expired card
        driver.findElement(By.id("month")).sendKeys("1"); // Expired month
        driver.findElement(By.id("year")).sendKeys("2023"); // Expired year

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        takeScreenshot("Attempted to purchase with expired card");
    }

    @Step("User verifies the error message for expired card")
    @Then("I should see an error message about the expired credit card")
    public void i_should_see_an_error_message_about_the_expired_credit_card() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Invalid card')]")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed!");
        takeScreenshot("Error message displayed");
        driver.quit();
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] takeScreenshot(String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

