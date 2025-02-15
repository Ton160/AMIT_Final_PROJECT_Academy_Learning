package Steps;

import Pages.LoginPageWithInvalidCredintials;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class LoginStepsWithInvalidSteps {

    WebDriver driver = new ChromeDriver();
    LoginPageWithInvalidCredintials loginPage = new LoginPageWithInvalidCredintials(driver);

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get("https://www.demoblaze.com");
    }

    @When("I click on the login button")
    public void iClickOnTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @And("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click on the login submit button")
    public void iClickOnTheLoginSubmitButton() {
        loginPage.clickLoginSubmitButton();
    }

    @Then("I should see an error alert with message {string}")
    public void iShouldSeeAnErrorAlertWithMessage(String expectedMessage) {
        assertEquals(expectedMessage, loginPage.getAlertMessage());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}