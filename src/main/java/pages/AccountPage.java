package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage {

    private By accountCreatedMessage = By.cssSelector("h2[data-qa='account-created']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");
    private By accountDeletedMessage = By.cssSelector("h2[data-qa='account-deleted']");

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
}