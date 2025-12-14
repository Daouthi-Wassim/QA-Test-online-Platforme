@deleteAccount @regression
Feature: Delete Account
  As a logged in user
  I want to delete my account
  So that I can remove my data from the system

  @smoke @pass
  Scenario: PASS - Successfully delete account
    Given I am logged in with email "testuser.demo123@example.com" and password "TestPass@123"
    When I navigate to delete account page
    Then I should see account deleted confirmation
    And I should see account deleted message
    When I click continue after account deletion
    Then I should be redirected to home page

  @fail
  Scenario: FAIL - Verify deleted account cannot login
    When I try to login with deleted account credentials
    Then I should see login error message
