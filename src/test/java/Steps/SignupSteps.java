
package Steps;

import Pages.SignupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupSteps {
    WebDriver driver;
    SignupPage signupPage;

    @Given("User is on the home page Actually")
    public void user_is_on_home_page_Actually() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
        signupPage = new SignupPage(driver);
    }

    @When("User clicks on the Sign up button Correctly")
    public void user_clicks_on_sign_up_Correctly() {
        signupPage.clickSignUp();
    }

    @And("User enters a new username and password")
    public void user_enters_credentials() {
        signupPage.enterUsername("Bavaaary");
        signupPage.enterPassword("151515");
    }

    @And("User clicks on the Sign up button Correct")
    public void user_clicks_on_sign_up_button() {
        signupPage.submitSignup();
    }

    @Then("User should see a success message {string}")
    public void user_should_see_success_message(String expectedMessage) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actualMessage = wait.until(ExpectedConditions.alertIsPresent()).getText();


        assert actualMessage.contains(expectedMessage);


        driver.switchTo().alert().accept();

        driver.quit();
    }
}