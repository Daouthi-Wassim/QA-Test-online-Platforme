@logout @regression
Feature: User Logout and Redirect
  As a logged in user
  I want to logout from the application
  So that I can end my session securely and be redirected to home page

  @smoke

  @pass
  Scenario: PASS - Successful logout and redirect to home page
    Given I am logged in with email "testuser.demo123@example.com" and password "TestPass@123"
    When I click the logout button
    And I should not see the user profile
