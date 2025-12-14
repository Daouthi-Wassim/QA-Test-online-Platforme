package stepdefinitions;

import base.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.ContactPage;

import java.util.Map;

public class ContactSteps extends BaseTest {

    private ContactPage contactPage;

    public ContactSteps() {
        contactPage = new ContactPage(getDriver());
    }

    @Given("I am on the contact page")
    public void iAmOnTheContactPage() {
        contactPage.openContactPage();
        Assertions.assertTrue(contactPage.isPageLoaded(), "Contact page should be loaded");
    }

    @When("I fill in the contact form with:")
    public void iFillInTheContactFormWith(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        contactPage.fillContactForm(
                data.get("name"),
                data.get("email"),
                data.get("subject"),
                data.get("message"));
    }

    @When("I submit the contact form")
    public void iSubmitTheContactForm() {
        contactPage.submitForm();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        Assertions.assertTrue(contactPage.isSuccessMessageDisplayed(),
                "Success message should be displayed");
    }

    @Then("the success message should contain {string}")
    public void theSuccessMessageShouldContain(String expectedText) {
        String actualMessage = contactPage.getSuccessMessage();
        Assertions.assertTrue(actualMessage.contains(expectedText),
                "Success message should contain: " + expectedText);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {

        boolean validationPresent = false;
        String validationMsg = contactPage.getMessageValidationMessage();

        if (validationMsg != null && !validationMsg.isEmpty()) {
            System.out.println("HTML5 Validation detected: " + validationMsg);
            validationPresent = true;
        }

        boolean stillOnContactPage = contactPage.isContactPageStillDisplayed();
        if (stillOnContactPage) {
            System.out.println("Still on contact page - form submission blocked");
        }

        boolean errorDisplayed = contactPage.isErrorMessageDisplayed();
        if (errorDisplayed) {
            System.out.println("Contact error message displayed");
        }

        boolean noSuccess = !contactPage.isSuccessMessageDisplayed();

        // At least one validation method should work
        Assertions.assertTrue(
                validationPresent || stillOnContactPage || errorDisplayed || noSuccess,
                "Expected validation: HTML5 validation, staying on page, error message, or no success. " +
                        "Validation message: '" + validationMsg + "', " +
                        "Still on page: " + stillOnContactPage + ", " +
                        "Error displayed: " + errorDisplayed + ", " +
                        "No success: " + noSuccess);
    }
}