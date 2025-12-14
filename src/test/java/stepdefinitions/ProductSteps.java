package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.ProductsPage;

public class ProductSteps extends BaseTest {

    private ProductsPage productsPage;

    public ProductSteps() {
        productsPage = new ProductsPage(getDriver());
    }

    @Given("I am on the products page")
    public void iAmOnTheProductsPage() {
        productsPage.openProductsPage();
    }

    @When("I search for product {string}")
    public void iSearchForProduct(String productName) {
        productsPage.searchProduct(productName);
    }

    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        Assertions.assertTrue(productsPage.isSearchResultsDisplayed(),
                "Search results should be displayed");
    }

    @Then("I should see at least {int} product")
    public void iShouldSeeAtLeastProduct(int expectedCount) {
        int actualCount = productsPage.getProductCount();
        Assertions.assertTrue(actualCount >= expectedCount,
                "Expected at least " + expectedCount + " products but found " + actualCount);
    }

    @When("I add the first product to cart")
    public void iAddTheFirstProductToCart() {
        productsPage.addFirstProductToCart();
    }

    @When("I click continue shopping")
    public void iClickContinueShopping() {
        productsPage.clickContinueShopping();
    }

    @When("I click view cart")
    public void iClickViewCart() {
        productsPage.clickViewCart();
    }

    @Then("I should see the products page title")
    public void iShouldSeeTheProductsPageTitle() {
        String title = productsPage.getPageTitle();
        Assertions.assertFalse(title.isEmpty(), "Products page title should be displayed");
    }

    @When("I select category {string}")
    public void iSelectCategory(String category) {
        productsPage.selectCategory(category);
    }
}