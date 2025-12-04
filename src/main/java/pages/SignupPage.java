package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {

    // Locators pour la page de création de compte
    private By accountInfoTitle = By.xpath("//b[contains(text(), 'Enter Account Information')]");
    private By titleMr = By.id("id_gender1");
    private By titleMrs = By.id("id_gender2");
    private By passwordInput = By.id("password");
    private By daysDropdown = By.id("days");
    private By monthsDropdown = By.id("months");
    private By yearsDropdown = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By specialOffersCheckbox = By.id("optin");

    // Informations d'adresse
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By companyInput = By.id("company");
    private By address1Input = By.id("address1");
    private By address2Input = By.id("address2");
    private By countryDropdown = By.id("country");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipcodeInput = By.id("zipcode");
    private By mobileNumberInput = By.id("mobile_number");
    private By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreationPageLoaded() {
        try {
            Thread.sleep(1000);
            return isElementDisplayed(accountInfoTitle) &&
                    isElementDisplayed(passwordInput) &&
                    isElementDisplayed(daysDropdown);
        } catch (Exception e) {
            return false;
        }
    }

    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("mr")) {
            clickElement(titleMr);
        } else if (title.equalsIgnoreCase("mrs")) {
            clickElement(titleMrs);
        }
    }

    public void enterPassword(String password) {
        sendKeysToElement(passwordInput, password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        selectDropdownByValue(daysDropdown, day);
        selectDropdownByValue(monthsDropdown, month);
        selectDropdownByValue(yearsDropdown, year);
    }

    public void checkNewsletter() {
        clickElement(newsletterCheckbox);
    }

    public void checkSpecialOffers() {
        clickElement(specialOffersCheckbox);
    }

    public void enterFirstName(String firstName) {
        sendKeysToElement(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeysToElement(lastNameInput, lastName);
    }

    public void enterCompany(String company) {
        sendKeysToElement(companyInput, company);
    }

    public void enterAddress1(String address) {
        sendKeysToElement(address1Input, address);
    }

    public void enterAddress2(String address) {
        sendKeysToElement(address2Input, address);
    }

    public void selectCountry(String country) {
        selectDropdownByVisibleText(countryDropdown, country);
    }

    public void enterState(String state) {
        sendKeysToElement(stateInput, state);
    }

    public void enterCity(String city) {
        sendKeysToElement(cityInput, city);
    }

    public void enterZipcode(String zipcode) {
        sendKeysToElement(zipcodeInput, zipcode);
    }

    public void enterMobileNumber(String mobile) {
        sendKeysToElement(mobileNumberInput, mobile);
    }

    public void clickCreateAccount() {
        clickElement(createAccountButton);
    }
}