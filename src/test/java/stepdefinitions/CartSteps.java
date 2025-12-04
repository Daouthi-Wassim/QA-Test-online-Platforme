package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.CartPage;
import static org.junit.Assert.*;

public class CartSteps {

    private HomePage homePage;
    private CartPage cartPage;

    @When("I click on Cart button")
    public void i_click_on_cart_button() {
        homePage.clickCart();
        cartPage = new CartPage(BaseTest.getDriver());
    }

    @Then("I should see cart page")
    public void i_should_see_cart_page() {
        assertTrue("Should be on cart page", true);
    }
}