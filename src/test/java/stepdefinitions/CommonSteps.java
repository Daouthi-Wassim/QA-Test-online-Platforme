package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class CommonSteps extends BaseTest {
    private By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private By loginButton = By.cssSelector("button[data-qa='login-button']");
    private By logoutLink = By.cssSelector("a[href='/logout']");
    private By loggedInUser = By.xpath("//li/a[contains(text(), 'Logged in as')]");
    private By deleteAccountLink = By.cssSelector("a[href='/delete_account']");

    @Given("I am logged in with email {string} and password {string}")
    public void iAmLoggedInWithEmailAndPassword(String email, String password) {
        getDriver().get("https://automationexercise.com/login");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform login
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailInput)).sendKeys(email);
        getDriver().findElement(loginPasswordInput).sendKeys(password);
        getDriver().findElement(loginButton).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify login
        System.out.println("Current URL after login: " + getDriver().getCurrentUrl());
        boolean isLoggedIn = isUserLoggedIn();
        System.out.println("Is user logged in: " + isLoggedIn);

        if (!isLoggedIn) {
            System.out.println("Login failed - checking page source for errors");
            String pageSource = getDriver().getPageSource();
            if (pageSource.contains("incorrect") || pageSource.contains("wrong")) {
                System.out.println("ERROR: Incorrect login credentials detected");
            }
        }

        Assertions.assertTrue(isLoggedIn, "User should be logged in");
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
