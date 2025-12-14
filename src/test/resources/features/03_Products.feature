@products @regression
Feature: Search Product and Add to Cart
  As a user
  I want to search for products and add them to my cart
  So that I can purchase items I need

  Background:
    Given I am on the products page

  @smoke
  @fail
  Scenario: FAIL - Search for non-existent product
    When I search for product "NonExistentProduct12345XYZ"
    Then I should see search results
    And I should see at least 0 product

  @pass
  Scenario: PASS - Search for dress and add to cart
    When I search for product "Dress"
    Then I should see search results
    And I should see at least 1 product
    When I add the first product to cart
    And I click continue shopping
    Then I should see the products page title


  @pass
  Scenario: PASS - Add multiple products to cart
    When I add the first product to cart
    And I click continue shopping
    And I add the first product to cart
    And I click view cart
    Then I should see 1 item in the cart



