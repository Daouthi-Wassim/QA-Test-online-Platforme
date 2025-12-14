package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductsPage extends BasePage {

    // Locators
    private By searchInput = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By productItems = By.cssSelector(".productinfo.text-center");
    private By addToCartButtons = By.cssSelector("a.add-to-cart");
    private By continueShoppingButton = By.cssSelector("button.btn-success");
    private By viewCartButton = By.xpath("//u[contains(text(),'View Cart')]");
    private By productsTitle = By.cssSelector(".features_items h2.title");
    private By searchResultsTitle = By.xpath("//h2[contains(text(),'Searched Products')]");
    private By firstProduct = By.cssSelector(".productinfo.text-center:first-child");
    private By categoryWomen = By.cssSelector("a[href='#Women']");
    private By categoryDress = By.cssSelector("a[href='/category_products/1']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void openProductsPage() {
        driver.get("https://automationexercise.com/products");
    }

    public void searchProduct(String productName) {
        sendKeysToElement(searchInput, productName);
        clickElement(searchButton);
    }

    public void addFirstProductToCart() {
        try {
            Thread.sleep(1000);

            List<WebElement> products = driver.findElements(addToCartButtons);
            if (!products.isEmpty()) {
                WebElement addButton = products.get(0);

                // Scroll to element
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);

                Thread.sleep(500);

                // Use JavaScript click to avoid ad interception
                try {
                    addButton.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", addButton);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickContinueShopping() {
        try {
            Thread.sleep(1000);
            if (isElementDisplayed(continueShoppingButton)) {
                clickElement(continueShoppingButton);
            }
        } catch (Exception e) {
            // Ignore if button not present
        }
    }

    public void clickViewCart() {
        try {
            Thread.sleep(1000);
            if (isElementDisplayed(viewCartButton)) {
                clickElement(viewCartButton);
            }
        } catch (Exception e) {
            // Ignore if button not present
        }
    }

    public boolean isSearchResultsDisplayed() {
        return isElementDisplayed(searchResultsTitle);
    }

    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }

    public String getPageTitle() {
        return getElementText(productsTitle);
    }

    public void selectCategory(String category) {
        if (category.equalsIgnoreCase("women")) {
            clickElement(categoryWomen);
            clickElement(categoryDress);
        }
    }
}