package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    // Locators
    private By commentTextarea = By.name("message");
    private By placeOrderButton = By.cssSelector("a[href='/payment']");
    private By nameOnCardInput = By.name("name_on_card");
    private By cardNumberInput = By.name("card_number");
    private By cvcInput = By.name("cvc");
    private By expiryMonthInput = By.name("expiry_month");
    private By expiryYearInput = By.name("expiry_year");
    private By payAndConfirmButton = By.id("submit");
    private By successMessage = By.id("success_message");
    private By orderPlacedMessage = By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void addComment(String comment) {
        sendKeysToElement(commentTextarea, comment);
    }

    public void clickPlaceOrder() {
        clickElement(placeOrderButton);
    }

    public void enterPaymentDetails(String name, String cardNumber, String cvc, String month, String year) {
        sendKeysToElement(nameOnCardInput, name);
        sendKeysToElement(cardNumberInput, cardNumber);
        sendKeysToElement(cvcInput, cvc);
        sendKeysToElement(expiryMonthInput, month);
        sendKeysToElement(expiryYearInput, year);
    }

    public void clickPayAndConfirm() {
        clickElement(payAndConfirmButton);
    }

    public boolean isOrderConfirmed() {
        return isElementDisplayed(orderPlacedMessage);
    }

    public String getSuccessMessage() {
        return getElementText(successMessage);
    }
}