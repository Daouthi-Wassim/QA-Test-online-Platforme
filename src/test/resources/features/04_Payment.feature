@payment @regression
Feature: Payment Processing
  As a logged in user
  I want to complete payment for my order
  So that I can purchase products

  Background:
    Given I am logged in with email "testuser.demo123@example.com" and password "TestPass@123"
    And I have items in my cart

  @smoke

  @fail
  Scenario: FAIL - Payment with empty card number
    When I proceed to payment
    And I enter payment details:
      | cardNumber |                     |
      | cardHolder | Test User             |
      | expiry     | 12/25                 |
      | cvv        | 123                   |
    And I submit the payment
    Then I should see payment error message


  @pass
  Scenario: PASS - Successful payment with valid Visa card
    When I proceed to payment
    And I enter payment details:
      | cardNumber | 4111111111111111      |
      | cardHolder | Test User Demo        |
      | expiry     | 12/25                 |
      | cvv        | 123                   |
    And I submit the payment
    Then I should see payment success message
    And I should see order confirmation
    When I download the invoice
    Then the invoice should be downloaded successfully

