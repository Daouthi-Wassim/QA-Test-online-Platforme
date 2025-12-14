@signup @regression
Feature: User Signup
  As a new user
  I want to create an account
  So that I can access the application features

  @smoke
  @fail
  Scenario: FAIL - Signup with already registered email
    Given I am on the signup page
    When I enter signup name "Existing User" and email "test123@example.com"
    And I click the signup button
    Then I should see signup error message "Email Address already exist!"

  @fail
  Scenario: FAIL - Signup with empty name
    Given I am on the signup page
    When I enter signup name "" and email "newuser@example.com"
    And I click the signup button
    Then I should remain on the login page


  @pass
  Scenario: PASS - Successful user registration with valid details
    Given I am on the signup page
    When I enter signup name "Test33" and email "testuser.demo123@example.com"
    And I click the signup button
    Then I should be redirected to the account creation page
    When I fill in the account details:
      | title     | Mr                |
      | password  | TestPass@123      |
      | day       | 15                |
      | month     | 6                 |
      | year      | 1990              |
      | firstName | Test              |
      | lastName  | Demo              |
      | company   | Demo Company      |
      | address1  | 123 Test Street   |
      | city      | New York          |
      | country   | United States     |
      | state     | NY                |
      | zipcode   | 10001             |
      | mobile    | 9876543210        |
    And I click create account
    Then I should see account created message
    When I click continue after account creation
    Then I should be logged in as "Test33"




