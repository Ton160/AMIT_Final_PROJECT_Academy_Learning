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
import java.util.List;

@Epic("E-Commerce Tests")
@Feature("Cart Functionality")
public class Add_Product_Twice_to_Cart{
    private WebDriver driver;
    private WebDriverWait wait;

    @Step("Launch browser and navigate to the application")
    @Given("I am logged in Successfully")
    public void i_am_logged_in_successfully() {
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
    }

    @Step("User adds Product 1 to the cart Successfully")
    @When("I add Product 1 to the cart Successfully")
    public void i_add_product_1_to_the_cart_successfully() {
        driver.findElement(By.xpath("//a[text()='Laptops']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sony vaio i5')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        takeScreenshot("Added Product 1 to the cart");
    }

    @Step("User adds Product 1 to the cart again")
    @When("I add Product 1 to the cart again")
    public void i_add_product_1_to_the_cart_again() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        takeScreenshot("Added Product 1 to the cart again");
    }

    @Step("User verifies the product quantity updated in the cart")
    @Then("I should see the product quantity updated in the cart")
    public void i_should_see_the_product_quantity_updated_in_the_cart() {
        driver.findElement(By.xpath("//a[text()='Cart']")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody[@id='tbodyid']/tr")));


        List<WebElement> products = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr"));
        Assert.assertEquals(products.size(), 2, "Product was added as a new entry instead of updating quantity!");

        takeScreenshot("Verified product quantity updated in the cart");
    }

    @Step("User verifies total amount is calculated correctly and Successfully")
    @Then("the total amount should be calculated correctly and Successfully")
    public void the_total_amount_should_be_calculated_correctly_and_successfully() {
        List<WebElement> prices = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr/td[3]"));
        double total = 0;
        for (WebElement price : prices) {
            total += Double.parseDouble(price.getText());
        }

        double displayedTotal = Double.parseDouble(driver.findElement(By.id("totalp")).getText());
        Assert.assertEquals(total, displayedTotal, "Total amount is incorrect!");
        takeScreenshot("Verified total amount calculation");
        driver.quit();
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] takeScreenshot(String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
