package stepdefinitions;

import base.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.SignupPage;

import java.util.Map;

public class SignupSteps extends BaseTest {

    private SignupPage signupPage;

    public SignupSteps() {
        signupPage = new SignupPage(getDriver());
    }

    @Given("I am on the signup page")
    public void iAmOnTheSignupPage() {
        signupPage.openSignupPage();
        Assertions.assertTrue(signupPage.isSignupPageDisplayed(), "Signup page should be displayed");
    }

    @When("I enter signup name {string} and email {string}")
    public void iEnterSignupNameAndEmail(String name, String email) {
        signupPage.enterSignupDetails(name, email);
    }

    @When("I click the signup button")
    public void iClickTheSignupButton() {
        signupPage.clickSignupButton();
    }

    @Then("I should be redirected to the account creation page")
    public void iShouldBeRedirectedToTheAccountCreationPage() {
        Assertions.assertTrue(signupPage.isAccountCreationPageLoaded(),
                "Account creation page should be loaded");
    }

    @When("I fill in the account details:")
    public void iFillInTheAccountDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        signupPage.selectTitle(data.get("title"));
        signupPage.enterPassword(data.get("password"));
        signupPage.selectDateOfBirth(data.get("day"), data.get("month"), data.get("year"));
        signupPage.enterFirstName(data.get("firstName"));
        signupPage.enterLastName(data.get("lastName"));
        signupPage.enterCompany(data.get("company"));
        signupPage.enterAddress1(data.get("address1"));
        signupPage.enterCity(data.get("city"));
        signupPage.selectCountry(data.get("country"));
        signupPage.enterState(data.get("state"));
        signupPage.enterZipcode(data.get("zipcode"));
        signupPage.enterMobileNumber(data.get("mobile"));
    }

    @When("I click create account")
    public void iClickCreateAccount() {
        signupPage.clickCreateAccount();
    }

    @Then("I should see account created message")
    public void iShouldSeeAccountCreatedMessage() {
        Assertions.assertTrue(signupPage.isAccountCreated(),
                "Account created message should be displayed");
    }

    @When("I click continue after account creation")
    public void iClickContinueAfterAccountCreation() {
        signupPage.clickContinue();
    }

    @Then("I should be logged in as {string}")
    public void iShouldBeLoggedInAs(String username) {
        Assertions.assertTrue(signupPage.isUserLoggedIn(), "User should be logged in");
        String loggedInUser = signupPage.getLoggedInUsername();
        Assertions.assertTrue(loggedInUser.contains(username),
                "Username should be " + username + " but was " + loggedInUser);
    }

    @Then("I should see signup error message {string}")
    public void iShouldSeeSignupErrorMessage(String expectedMessage) {
        Assertions.assertTrue(signupPage.isSignupErrorDisplayed(),
                "Signup error message should be displayed");
        String actualMessage = signupPage.getSignupErrorMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage),
                "Error message should contain: " + expectedMessage);
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        Assertions.assertTrue(signupPage.isSignupPageDisplayed(),
                "Should remain on signup/login page");
    }
}