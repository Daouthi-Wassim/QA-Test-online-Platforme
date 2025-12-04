Feature: Shopping Cart Management

  @Cart
  Scenario: View cart items
    Given I have items in my cart
    When I view my cart
    Then I should see cart items

  @Cart
  Scenario: Remove item from cart
    Given I have items in my cart
    When I remove first item from cart
    Then I should see cart updated