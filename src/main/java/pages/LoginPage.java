package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Sélecteurs CORRECTS pour automationexercise.com
    private By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");

    // Sélecteurs pour SIGNUP - Section Signup à droite
    private By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private By signupButton = By.cssSelector("button[data-qa='signup-button']");

    private By loginError = By.cssSelector("p[style='color: red;']");
    private By signupError = By.xpath("//form[@action='/signup']//p[contains(@style, 'red')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Méthodes pour LOGIN
    public void enterLoginEmail(String email) {
        sendKeysToElement(loginEmailInput, email);
    }

    public void enterLoginPassword(String password) {
        sendKeysToElement(loginPasswordInput, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    // Méthodes pour SIGNUP
    public void enterSignupName(String name) {
        // Attendre que la page soit chargée
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeysToElement(signupNameInput, name);
    }

    public void enterSignupEmail(String email) {
        sendKeysToElement(signupEmailInput, email);
    }

    public void clickSignupButton() {
        System.out.println("Clicking signup button...");
        clickElement(signupButton);
        // Attendre que la page se charge
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getLoginErrorMessage() {
        return getElementText(loginError);
    }

    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(loginError);
    }

    public boolean isSignupErrorDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementDisplayed(signupError);
    }

    public String getSignupErrorMessage() {
        return getElementText(signupError);
    }

    // Vérifier qu'on est bien sur la page de login/signup
    public boolean isLoginPageLoaded() {
        try {
            return waitForElementVisible(signupNameInput).isDisplayed() &&
                    waitForElementVisible(signupEmailInput).isDisplayed();
        } catch (Exception e) {
            System.out.println("Login page not loaded properly: " + e.getMessage());
            return false;
        }
    }
}