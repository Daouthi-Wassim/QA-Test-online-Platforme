Feature: User Authentication

  @Signup
  Scenario: New user registration and login
    Given I am on the homepage
    When I click on Signup / Login button
    And I enter signup name "TestUser" and unique email
    And I click signup button
    Then I should be redirected to account creation page
    When I complete account creation with password "password123"
    And I click continue button
    Then I should be logged in successfully
    When I click logout button
    Then I should be logged out

  @Login
  Scenario: Login with valid credentials
    Given I am on the homepage
    When I click on Signup / Login button
    And I enter login email "existing@example.com" and password "password123"
    And I click login button
    Then I should be logged in successfully

  @Login
  Scenario: Login with invalid credentials
    Given I am on the homepage
    When I click on Signup / Login button
    And I enter login email "invalid@example.com" and password "wrongpassword"
    And I click login button
    Then I should see login error message