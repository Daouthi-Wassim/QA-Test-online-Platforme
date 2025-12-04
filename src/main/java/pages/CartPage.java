package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {

    // Locators
    private By cartItems = By.xpath("//table[@id='cart_info_table']//tbody/tr");
    private By deleteButtons = By.cssSelector("a.cart_quantity_delete");
    private By emptyCartMessage = By.id("empty_cart");
    private By proceedToCheckoutButton = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    private By quantityInputs = By.cssSelector("input.cart_quantity_input");
    private By cartTotal = By.cssSelector("td.cart_total");
    private By productName = By.cssSelector("td.cart_description h4 a");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCartPage() {
        driver.get("https://automationexercise.com/view_cart");
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public void removeFirstItemFromCart() {
        List<WebElement> deleteBtns = driver.findElements(deleteButtons);
        if (!deleteBtns.isEmpty()) {
            deleteBtns.get(0).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isCartEmpty() {
        return isElementDisplayed(emptyCartMessage);
    }

    public void proceedToCheckout() {
        clickElement(proceedToCheckoutButton);
    }

    public void updateQuantity(int itemIndex, String quantity) {
        List<WebElement> inputs = driver.findElements(quantityInputs);
        if (itemIndex < inputs.size()) {
            WebElement input = inputs.get(itemIndex);
            input.clear();
            input.sendKeys(quantity);
        }
    }

    public String getProductName(int index) {
        List<WebElement> names = driver.findElements(productName);
        if (index < names.size()) {
            return names.get(index).getText();
        }
        return "";
    }

    public String getTotalPrice() {
        List<WebElement> totals = driver.findElements(cartTotal);
        if (!totals.isEmpty()) {
            return totals.get(0).getText();
        }
        return "";
    }
}