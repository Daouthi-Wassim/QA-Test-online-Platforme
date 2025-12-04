package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupSteps {

    private HomePage homePage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private AccountPage accountPage;
    private String generatedEmail;

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        homePage = new HomePage(BaseTest.getDriver());
        assertTrue("Homepage should be loaded", BaseTest.getDriver().getCurrentUrl().contains("automationexercise.com"));
    }

    @When("I click on Signup \\/ Login button")
    public void i_click_on_signup_login_button() {
        homePage.clickLogin();
        loginPage = new LoginPage(BaseTest.getDriver());
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        assertTrue("Login page should be loaded", loginPage.isLoginPageLoaded());
    }

    @When("I enter signup name {string} and unique email")
    public void i_enter_signup_name_and_unique_email(String name) {
        // Générer un email unique
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        generatedEmail = "testuser_" + timestamp + "@example.com";

        System.out.println("Generated email: " + generatedEmail);
        loginPage.enterSignupName(name);
        loginPage.enterSignupEmail(generatedEmail);
    }

    @When("I click signup button")
    public void i_click_signup_button() {
        loginPage.clickSignupButton();
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Then("I should be redirected to account creation page")
    public void i_should_be_redirected_to_account_creation_page() {
        signupPage = new SignupPage(BaseTest.getDriver());
        assertTrue("Should be on account creation page", signupPage.isAccountCreationPageLoaded());
    }

    @When("I complete account creation with password {string}")
    public void i_complete_account_creation_with_password(String password) {
        // Remplir le formulaire de création de compte
        signupPage.selectTitle("Mr");
        signupPage.enterPassword(password);
        signupPage.selectDateOfBirth("1", "1", "1990");
        signupPage.checkNewsletter();
        signupPage.checkSpecialOffers();

        // Informations d'adresse
        signupPage.enterFirstName("John");
        signupPage.enterLastName("Doe");
        signupPage.enterCompany("Test Company");
        signupPage.enterAddress1("123 Test Street");
        signupPage.enterAddress2("Apt 4B");
        signupPage.selectCountry("United States");
        signupPage.enterState("California");
        signupPage.enterCity("Los Angeles");
        signupPage.enterZipcode("90001");
        signupPage.enterMobileNumber("1234567890");

        // Cliquer sur créer le compte
        signupPage.clickCreateAccount();
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @When("I click continue button")
    public void i_click_continue_button() {
        accountPage = new AccountPage(BaseTest.getDriver());
        accountPage.clickContinue();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        homePage = new HomePage(BaseTest.getDriver());
        assertTrue("User should be logged in", homePage.isUserLoggedIn());
        System.out.println("User logged in as: " + homePage.getLoggedInUsername());
    }

    @When("I click logout button")
    public void i_click_logout_button() {
        homePage.clickLogout();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Then("I should be logged out")
    public void i_should_be_logged_out() {
        homePage = new HomePage(BaseTest.getDriver());
        assertFalse("User should be logged out", homePage.isUserLoggedIn());
    }
}