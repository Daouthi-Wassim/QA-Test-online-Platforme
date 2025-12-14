package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.CartPage;

public class CartSteps extends BaseTest {

    private CartPage cartPage;

    public CartSteps() {
        cartPage = new CartPage(getDriver());
    }

    @Given("I am on the cart page")
    public void iAmOnTheCartPage() {
        cartPage.openCartPage();
    }

    @Then("I should see {int} item in the cart")
    public void iShouldSeeItemInTheCart(int expectedCount) {
        int actualCount = cartPage.getCartItemCount();
        Assertions.assertEquals(expectedCount, actualCount,
                "Expected " + expectedCount + " items but found " + actualCount);
    }

    @Then("I should see {int} items in the cart")
    public void iShouldSeeItemsInTheCart(int expectedCount) {
        iShouldSeeItemInTheCart(expectedCount);
    }

    @Then("the total price should be displayed")
    public void theTotalPriceShouldBeDisplayed() {
        String totalPrice = cartPage.getTotalPrice();
        Assertions.assertFalse(totalPrice.isEmpty(), "Total price should be displayed");
    }

    @When("I remove the first item from cart")
    public void iRemoveTheFirstItemFromCart() {
        cartPage.removeFirstItemFromCart();
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isEmpty = cartPage.isCartEmpty() || cartPage.getCartItemCount() == 0;
        Assertions.assertTrue(isEmpty, "Cart should be empty");
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        cartPage.proceedToCheckout();
    }

    @Then("I should remain on the cart page")
    public void iShouldRemainOnTheCartPage() {
        String currentUrl = getDriver().getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("view_cart"),
                "Should remain on cart page");
    }
}