Feature: Products Management

  @Products
  Scenario: Search for products
    Given I am logged in with email "test@example.com" and password "password123"
    When I navigate to products page
    And I search for product "T-Shirt"
    Then I should see search results

  @Products
  Scenario: Add product to cart
    Given I am logged in with email "test@example.com" and password "password123"
    When I navigate to products page
    And I add first product to cart
    And I view cart
    Then I should see at least 1 item in cart