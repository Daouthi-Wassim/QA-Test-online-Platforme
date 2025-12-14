@cart @regression
Feature: View Cart, Remove Items and Payment
  As a user
  I want to manage my shopping cart and complete payment
  So that I can purchase products

  @smoke
  @pass
  Scenario: FAIL - Remove all items then verify cart is empty
    Given I am on the products page
    When I add the first product to cart
    And I click view cart
    When I remove the first item from cart
    Then the cart should be empty
    And I should see 0 items in the cart

  @pass
  Scenario: PASS - View cart with items
    Given I am on the products page
    When I add the first product to cart
    And I click view cart
    Then I should see 1 item in the cart
    And the total price should be displayed






