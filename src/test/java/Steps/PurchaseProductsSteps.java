package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

@Epic("E-Commerce Tests")
@Feature("Purchase Products Feature")
public class PurchaseProductsSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Step("Launch browser and navigate to the application")
    @Given("I am logged in")
    public void i_am_logged_in() {
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

        String welcomeMessage = driver.findElement(By.id("nameofuser")).getText();
        Assert.assertTrue(welcomeMessage.contains("Welcome"), "Login failed!");
    }

    @Step("User adds Product 1 to the cart")
    @Story("Adding products to the cart")
    @When("I add Product 1 to the cart")
    public void i_add_product_1_to_the_cart() {
        driver.findElement(By.xpath("//a[text()='Laptops']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sony vaio i5')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        takeScreenshot("Added Product 1 to the cart");
    }

    @Step("User adds Product 2 to the cart")
    @Story("Adding products to the cart")
    @When("I add Product 2 to the cart")
    public void i_add_product_2_to_the_cart() {
        driver.findElement(By.xpath("//a[contains(text(), 'Home')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Phones']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Samsung galaxy s7')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        takeScreenshot("Added Product 2 to the cart");
    }

    @Step("User verifies both products are in the cart")
    @Story("Checking cart contents")
    @Then("I should see both products in the cart")
    public void i_should_see_both_products_in_the_cart() {
        driver.findElement(By.xpath("//a[text()='Cart']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody[@id='tbodyid']/tr")));

        List<WebElement> products = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr"));
        Assert.assertEquals(products.size(), 2, "Not all products are in the cart!");
        takeScreenshot("Verified products in the cart");
    }

    @Step("User verifies total amount is calculated correctly")
    @Story("Cart checkout process")
    @Then("the total amount should be calculated correctly")
    public void the_total_amount_should_be_calculated_correctly() {
        List<WebElement> prices = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr/td[3]"));
        double total = 0;
        for (WebElement price : prices) {
            total += Double.parseDouble(price.getText());
        }

        double displayedTotal = Double.parseDouble(driver.findElement(By.id("totalp")).getText());
        Assert.assertEquals(total, displayedTotal, "Total amount is incorrect!");
        takeScreenshot("Verified total amount");
    }

    @Step("User proceeds to checkout")
    @Story("Completing the purchase")
    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));
        driver.findElement(By.id("name")).sendKeys("Antonious");
        driver.findElement(By.id("country")).sendKeys("Germany");
        driver.findElement(By.id("city")).sendKeys("Dortmund");
        driver.findElement(By.id("card")).sendKeys("1234567890123456");
        driver.findElement(By.id("month")).sendKeys("2");
        driver.findElement(By.id("year")).sendKeys("2025");

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        takeScreenshot("Checkout process completed");
    }

    @Step("User verifies the purchase success message")
    @Story("Completing the purchase")
    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']")));
        String successMessage = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']")).getText();
        Assert.assertTrue(successMessage.contains("Thank you for your purchase"), "Purchase was not successful!");

        takeScreenshot("Purchase success message displayed");
        driver.quit();
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] takeScreenshot(String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
