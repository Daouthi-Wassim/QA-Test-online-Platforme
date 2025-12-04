package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.ContactPage;
//import utils.ScreenshotHelper;
import static org.junit.Assert.*;

public class ContactSteps {

    private HomePage homePage;
    private ContactPage contactPage;

    @When("I navigate to contact page")
    public void i_navigate_to_contact_page() {
        homePage.clickContact();
        contactPage = new ContactPage(BaseTest.getDriver());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //ScreenshotHelper.takeScreenshot("contact_page");
        assertTrue("Should be on contact page", contactPage.isPageLoaded());
    }

    @When("I fill contact form with name {string} email {string} subject {string} message {string}")
    public void i_fill_contact_form(String name, String email, String subject, String message) {
        contactPage.fillContactForm(name, email, subject, message);
        //ScreenshotHelper.takeScreenshot("contact_form_filled");
    }

    @When("I submit contact form")
    public void i_submit_contact_form() {
        contactPage.submitForm();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //ScreenshotHelper.takeScreenshot("contact_form_submitted");
    }

    @Then("I should see contact success message")
    public void i_should_see_contact_success_message() {
        assertTrue("Contact success message should be displayed", contactPage.isSuccessMessageDisplayed());
        String message = contactPage.getSuccessMessage();
        System.out.println("Contact success message: " + message);
    }

    @Then("I should see contact error message")
    public void i_should_see_contact_error_message() {
        assertTrue("Contact error message should be displayed", contactPage.isErrorMessageDisplayed());
        String error = contactPage.getErrorMessage();
        System.out.println("Contact error message: " + error);
    }
}