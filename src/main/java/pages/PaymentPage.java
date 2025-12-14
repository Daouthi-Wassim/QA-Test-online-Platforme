package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {

    private By nameOnCardInput = By.cssSelector("input[data-qa='name-on-card']");
    private By cardNumberInput = By.cssSelector("input[data-qa='card-number']");
    private By cvcInput = By.cssSelector("input[data-qa='cvc']");
    private By expirationMonthInput = By.cssSelector("input[data-qa='expiry-month']");
    private By expirationYearInput = By.cssSelector("input[data-qa='expiry-year']");
    private By payButton = By.cssSelector("button[data-qa='pay-button']");
    private By successMessage = By.cssSelector(".alert-success");
    private By errorMessage = By.cssSelector(".alert-danger");
    private By orderConfirmation = By.xpath("//p[contains(text(),'Congratulations')]");
    private By downloadInvoiceButton = By.linkText("Download Invoice");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void enterPaymentDetails(String cardNumber, String cardHolder, String expiry, String cvv) {
        try {
            Thread.sleep(1000);
            if (isElementDisplayed(nameOnCardInput)) {
                sendKeysToElement(nameOnCardInput, cardHolder);
                sendKeysToElement(cardNumberInput, cardNumber);
                sendKeysToElement(cvcInput, cvv);
                String[] parts = expiry.split("/");
                if (parts.length == 2) {
                    sendKeysToElement(expirationMonthInput, parts[0]);
                    sendKeysToElement(expirationYearInput, parts[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submitPayment() {
        try {
            Thread.sleep(1000);
            if (isElementDisplayed(payButton)) {
                clickElement(payButton);
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPaymentSuccessful() {
        return isElementDisplayed(successMessage) ||
                isElementDisplayed(orderConfirmation);
    }

    public boolean isPaymentErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getSuccessMessage() {
        if (isElementDisplayed(orderConfirmation)) {
            return getElementText(orderConfirmation);
        }
        return getElementText(successMessage);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    // Validation checking methods
    public boolean isCardNumberRequired() {
        try {
            String required = driver.findElement(cardNumberInput).getAttribute("required");
            return required != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPaymentPageStillDisplayed() {
        return isElementDisplayed(payButton) && isElementDisplayed(cardNumberInput);
    }

    public String getCardNumberValidationMessage() {
        try {
            return driver.findElement(cardNumberInput).getAttribute("validationMessage");
        } catch (Exception e) {
            return "";
        }
    }

    // Invoice download methods
    public boolean isDownloadInvoiceButtonDisplayed() {
        try {
            return isElementDisplayed(downloadInvoiceButton);
        } catch (Exception e) {
            return false;
        }
    }

    public void downloadInvoice() {
        try {
            if (isElementDisplayed(downloadInvoiceButton)) {
                System.out.println("Clicking Download Invoice button...");
                clickElement(downloadInvoiceButton);
                Thread.sleep(2000); // Wait for download to start
                System.out.println("Invoice download initiated");
            } else {
                System.out.println("Download Invoice button not found");
            }
        } catch (Exception e) {
            System.err.println("Error downloading invoice: " + e.getMessage());
        }
    }

    public void clickContinue() {
        try {
            if (isElementDisplayed(continueButton)) {
                clickElement(continueButton);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("Continue button not found: " + e.getMessage());
        }
    }
}