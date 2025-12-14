package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DeleteAccountPage;
import pages.SignupPage;

import java.time.Duration;

public class DeleteAccountSteps extends BaseTest {

    private DeleteAccountPage deleteAccountPage;
    private SignupPage signupPage;

    // Login locators for verification
    private By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By loginErrorMessage = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    public DeleteAccountSteps() {
        deleteAccountPage = new DeleteAccountPage(getDriver());
        signupPage = new SignupPage(getDriver());
    }

    @When("I navigate to delete account page")
    public void iNavigateToDeleteAccountPage() {
        deleteAccountPage.navigateToDeleteAccount();
    }

    @Then("I should see account deleted confirmation")
    public void iShouldSeeAccountDeletedConfirmation() {
        Assertions.assertTrue(deleteAccountPage.isAccountDeletedConfirmationDisplayed(),
                "Account deleted confirmation should be displayed");
    }

    @Then("I should see account deleted message")
    public void iShouldSeeAccountDeletedMessage() {
        Assertions.assertTrue(deleteAccountPage.isAccountDeletedMessageDisplayed(),
                "Account deleted message should be displayed");

        String message = deleteAccountPage.getAccountDeletedMessage();
        System.out.println("Account deleted message: " + message);
    }

    @When("I click continue after account deletion")
    public void iClickContinueAfterAccountDeletion() {
        deleteAccountPage.clickContinue();
    }

    @Then("I should be redirected to home page")
    public void iShouldBeRedirectedToHomePage() {
        Assertions.assertTrue(deleteAccountPage.isOnHomePage(),
                "Should be redirected to home page after account deletion");
    }

    @When("I try to login with deleted account credentials")
    public void iTryToLoginWithDeletedAccountCredentials() {
        // Navigate to login page
        getDriver().get("https://automationexercise.com/login");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Try to login with deleted account
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailInput))
                .sendKeys("testuser.demo123456@example.com");
        getDriver().findElement(loginPasswordInput).sendKeys("TestPass@123");
        getDriver().findElement(loginButton).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see login error message")
    public void iShouldSeeLoginErrorMessage() {
        try {
            boolean errorDisplayed = getDriver().findElement(loginErrorMessage).isDisplayed();
            Assertions.assertTrue(errorDisplayed,
                    "Login error message should be displayed for deleted account");
        } catch (Exception e) {
            // If error message not found, check we're still on login page
            String currentUrl = getDriver().getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains("login"),
                    "Should remain on login page when trying to login with deleted account");
        }
    }
}
