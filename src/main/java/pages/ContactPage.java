package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    // Locators
    private By nameInput = By.name("name");
    private By emailInput = By.name("email");
    private By subjectInput = By.name("subject");
    private By messageTextarea = By.id("message");
    private By submitButton = By.name("submit");
    private By successMessage = By.cssSelector(".alert-success");
    private By errorMessage = By.cssSelector(".alert-danger");
    private By contactTitle = By.xpath("//h2[contains(text(),'Get In Touch')]");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void openContactPage() {
        driver.get("https://automationexercise.com/contact_us");
    }

    public void fillContactForm(String name, String email, String subject, String message) {
        sendKeysToElement(nameInput, name);
        sendKeysToElement(emailInput, email);
        sendKeysToElement(subjectInput, subject);
        sendKeysToElement(messageTextarea, message);
    }

    public void submitForm() {
        clickElement(submitButton);
        try {
            // Gérer l'alerte si elle apparaît
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // Pas d'alerte, continuer
        }
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getSuccessMessage() {
        return getElementText(successMessage);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public boolean isPageLoaded() {
        return isElementDisplayed(contactTitle);
    }
}