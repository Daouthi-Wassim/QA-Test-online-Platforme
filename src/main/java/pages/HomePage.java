package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // Locators CORRECTS vérifiés sur automationexercise.com
    private By loginLink = By.cssSelector("a[href='/login']");
    private By productsLink = By.cssSelector("a[href='/products']");
    private By cartLink = By.cssSelector("a[href='/view_cart']");
    private By contactLink = By.cssSelector("a[href='/contact_us']");
    private By homeLink = By.cssSelector("a[href='/']");
    private By testCasesLink = By.cssSelector("a[href='/test_cases']");
    private By apiTestingLink = By.cssSelector("a[href='/api_list']");
    private By videoTutorialsLink = By.cssSelector("a[href='https://www.youtube.com/c/AutomationExercise']");
    private By logoutLink = By.cssSelector("a[href='/logout']");
    private By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private By loggedInUser = By.cssSelector("li:nth-child(10) a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get("https://automationexercise.com");
        // Attendre que la page soit chargée
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickLogin() {
        clickElement(loginLink);
    }

    public void clickProducts() {
        clickElement(productsLink);
    }

    public void clickCart() {
        clickElement(cartLink);
    }

    public void clickContact() {
        clickElement(contactLink);
    }

    public void clickHome() {
        clickElement(homeLink);
    }

    public void clickLogout() {
        if (isElementDisplayed(logoutLink)) {
            clickElement(logoutLink);
        }
    }

    public void clickDeleteAccount() {
        if (isElementDisplayed(deleteAccountLink)) {
            clickElement(deleteAccountLink);
        }
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(loggedInUser);
    }

    public String getLoggedInUsername() {
        return getElementText(loggedInUser);
    }
}