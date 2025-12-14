package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private By accountCreatedMessage = By.cssSelector("h2[data-qa='account-created']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");
    private By accountDeletedMessage = By.cssSelector("h2[data-qa='account-deleted']");
    private By myAccountLink = By.cssSelector("a[href='/account']");
    private By profileNameInput = By.name("name");
    private By profileEmailInput = By.name("email");
    private By updateProfileButton = By.cssSelector("button[data-qa='update-profile']");
    private By profileUpdatedMessage = By.cssSelector(".alert-success");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreated() {
        return isElementDisplayed(accountCreatedMessage);
    }

    public String getAccountCreatedMessage() {
        return getElementText(accountCreatedMessage);
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public boolean isAccountDeleted() {
        return isElementDisplayed(accountDeletedMessage);
    }

    public void navigateToMyAccount() {
        clickElement(myAccountLink);
    }

    public void updateProfileInfo(String newName, String newEmail) {
        sendKeysToElement(profileNameInput, newName);
        sendKeysToElement(profileEmailInput, newEmail);
    }

    public void clickUpdateProfile() {
        clickElement(updateProfileButton);
    }

    public boolean isProfileUpdated() {
        return isElementDisplayed(profileUpdatedMessage);
    }

    public String getProfileUpdateMessage() {
        return getElementText(profileUpdatedMessage);
    }
}