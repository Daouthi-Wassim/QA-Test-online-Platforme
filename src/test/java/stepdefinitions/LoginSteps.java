package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.LoginPage;
import static org.junit.Assert.*;

public class LoginSteps {

    private HomePage homePage;
    private LoginPage loginPage;

    @When("I enter login email {string} and password {string}")
    public void i_enter_login_email_and_password(String email, String password) {
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
    }

    @When("I click login button")
    public void i_click_login_button() {
        loginPage.clickLoginButton();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Then("I should see login error message")
    public void i_should_see_login_error_message() {
        assertTrue("Login error should be displayed", loginPage.isLoginErrorDisplayed());
        System.out.println("Error message: " + loginPage.getLoginErrorMessage());
    }
}