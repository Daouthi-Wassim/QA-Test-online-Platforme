package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickElement(By locator) {
        try {
            WebElement element = waitForElementClickable(locator);
            element.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if regular click fails
            WebElement element = waitForElement(locator);
            js.executeScript("arguments[0].click();", element);
        }
    }

    protected void clickElementJS(By locator) {
        WebElement element = waitForElement(locator);
        js.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(By locator) {
        WebElement element = waitForElement(locator);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected void sendKeysToElement(By locator, String text) {
        WebElement element = waitForElementVisible(locator);
        element.clear();
        // Handle null or empty strings gracefully
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        }
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected String getElementText(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            return element.getText();
        } catch (Exception e) {
            return "";
        }
    }

    protected void selectDropdownByValue(By locator, String value) {
        WebElement element = waitForElement(locator);
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
    }

    protected void selectDropdownByVisibleText(By locator, String text) {
        WebElement element = waitForElement(locator);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    protected void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // No alert present, continue
        }
    }

    protected void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            // No alert present, continue
        }
    }
}