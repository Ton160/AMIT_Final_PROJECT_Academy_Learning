package Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Signup_With_existing_Username_Steps {
    WebDriver driver;
    Pages.Signup_With_existing_Username signupPage;

    @Given("User is on the home page")
    public void user_is_on_home_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
        signupPage = new Pages.Signup_With_existing_Username(driver);
    }

    @When("User clicks on the Sign up button")
    public void user_clicks_on_sign_up() {
        signupPage.clickSignUp();
    }

    @And("User enters an existing username and a password")
    public void user_enters_existing_username() {
        signupPage.enterUsername("salaaah");  // اسم مستخدم موجود بالفعل
        signupPage.enterPassword("151515");
    }

    @And("User clicks on the Sign Up button")
    public void user_clicks_on_sign_up_button() {
        signupPage.submitSignup();
    }

    @Then("User should see an error message {string}")
    public void user_should_see_error_message(String expectedMessage) {
        String actualMessage = signupPage.getAlertMessage();


        assert actualMessage.equals(expectedMessage) :
                "Expected: " + expectedMessage + " but got: " + actualMessage;


        driver.quit();
    }
}
