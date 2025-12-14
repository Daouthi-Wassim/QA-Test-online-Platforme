package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class LogoutSteps extends BaseTest {

    // Logout locators
    private By logoutLink = By.cssSelector("a[href='/logout']");
    private By loggedInUser = By.xpath("//li/a[contains(text(), 'Logged in as')]");
    private By deleteAccountLink = By.cssSelector("a[href='/delete_account']");

    public LogoutSteps() {
    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        try {
            if (getDriver().findElement(logoutLink).isDisplayed()) {
                getDriver().findElement(logoutLink).click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            // Already logged out or logout link not found
        }
    }

    @Then("I should not see the user profile")
    public void iShouldNotSeeTheUserProfile() {
        boolean isLoggedIn = isUserLoggedIn();
        Assertions.assertFalse(isLoggedIn, "User should be logged out");
    }

    private boolean isUserLoggedIn() {
        try {
            return getDriver().findElement(logoutLink).isDisplayed() ||
                    getDriver().findElement(loggedInUser).isDisplayed() ||
                    getDriver().findElement(deleteAccountLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}