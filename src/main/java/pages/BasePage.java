package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Augmenté à 20s
    }

    protected WebElement waitForElement(By locator) {
        System.out.println("Waiting for element: " + locator);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            System.out.println("Element found: " + locator);
            return element;
        } catch (Exception e) {
            System.out.println("Element not found: " + locator);
            System.out.println("Current URL: " + driver.getCurrentUrl());
            throw e;
        }
    }

    protected WebElement waitForElementVisible(By locator) {
        System.out.println("Waiting for element visible: " + locator);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("Element visible: " + locator);
            return element;
        } catch (Exception e) {
            System.out.println("Element not visible: " + locator);
            throw e;
        }
    }

    protected WebElement waitForElementClickable(By locator) {
        System.out.println("Waiting for element clickable: " + locator);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            System.out.println("Element clickable: " + locator);
            return element;
        } catch (Exception e) {
            System.out.println("Element not clickable: " + locator);
            throw e;
        }
    }

    protected void clickElement(By locator) {
        try {
            WebElement element = waitForElementClickable(locator);
            System.out.println("Clicking element: " + locator);
            element.click();
            System.out.println("Clicked element: " + locator);
        } catch (Exception e) {
            System.out.println("Failed to click element: " + locator);
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    protected void sendKeysToElement(By locator, String text) {
        try {
            WebElement element = waitForElementVisible(locator);
            System.out.println("Sending keys to element: " + locator + " with text: " + text);
            element.clear();
            element.sendKeys(text);
            System.out.println("Sent keys to element: " + locator);
        } catch (Exception e) {
            System.out.println("Failed to send keys to element: " + locator);
            throw e;
        }
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            boolean displayed = element.isDisplayed();
            System.out.println("Element displayed check for " + locator + ": " + displayed);
            return displayed;
        } catch (Exception e) {
            System.out.println("Element not displayed: " + locator);
            return false;
        }
    }

    protected String getElementText(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            String text = element.getText();
            System.out.println("Element text for " + locator + ": " + text);
            return text;
        } catch (Exception e) {
            System.out.println("Element not found or not visible: " + locator);
            return "Element not found";
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
}