// ProductSteps.java
package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.*;
import static org.junit.Assert.*;

public class ProductSteps {

    private HomePage homePage;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @Given("I am logged in with email {string} and password {string}")
    public void i_am_logged_in_with_email_and_password(String email, String password) {
        homePage = new HomePage(BaseTest.getDriver());
        homePage.clickLogin();
        loginPage = new LoginPage(BaseTest.getDriver());
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
        loginPage.clickLoginButton();
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        assertTrue("Should be logged in", homePage.isUserLoggedIn());
    }

    @When("I navigate to products page")
    public void i_navigate_to_products_page() {
        homePage.clickProducts();
        productsPage = new ProductsPage(BaseTest.getDriver());
    }

    @When("I search for product {string}")
    public void i_search_for_product(String productName) {
        productsPage.searchProduct(productName);
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
        assertTrue("Search results should be visible", productsPage.isSearchResultsDisplayed());
    }

    @When("I add first product to cart")
    public void i_add_first_product_to_cart() {
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
    }

    @When("I view cart")
    public void i_view_cart() {
        homePage.clickCart();
        cartPage = new CartPage(BaseTest.getDriver());
    }

    @Then("I should see at least {int} item in cart")
    public void i_should_see_at_least_item_in_cart(int minItems) {
        assertTrue("Cart should have at least " + minItems + " items",
                cartPage.getCartItemCount() >= minItems);
    }
}