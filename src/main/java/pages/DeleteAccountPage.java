package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeleteAccountPage extends BasePage {

    // Locators
    private By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private By accountDeletedTitle = By.xpath("//h2[@data-qa='account-deleted']");
    private By accountDeletedMessage = By.xpath("//p[contains(text(),'Your account has been permanently deleted!')]");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public DeleteAccountPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToDeleteAccount() {
        try {
            if (isElementDisplayed(deleteAccountLink)) {
                System.out.println("Clicking Delete Account link...");
                clickElement(deleteAccountLink);
                Thread.sleep(2000);
            } else {
                System.out.println("Delete Account link not found");
            }
        } catch (Exception e) {
            System.err.println("Error navigating to delete account: " + e.getMessage());
        }
    }

    public boolean isAccountDeletedConfirmationDisplayed() {
        try {
            return isElementDisplayed(accountDeletedTitle);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAccountDeletedMessageDisplayed() {
        try {
            return isElementDisplayed(accountDeletedMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public String getAccountDeletedMessage() {
        try {
            if (isElementDisplayed(accountDeletedMessage)) {
                return getElementText(accountDeletedMessage);
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public void clickContinue() {
        try {
            if (isElementDisplayed(continueButton)) {
                System.out.println("Clicking Continue button...");
                clickElement(continueButton);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.err.println("Continue button not found: " + e.getMessage());
        }
    }

    public boolean isOnHomePage() {
        try {
            String currentUrl = driver.getCurrentUrl();
            return currentUrl.contains("automationexercise.com") &&
                    !currentUrl.contains("delete_account");
        } catch (Exception e) {
            return false;
        }
    }
}
