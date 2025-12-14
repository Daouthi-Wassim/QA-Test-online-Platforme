@contact @regression
Feature: Contact Form
  As a user
  I want to contact the support team
  So that I can get help or send feedback

  Background:
    Given I am on the contact page

  @smoke
  @fail
  Scenario: FAIL - Submit contact form with empty
    When I fill in the contact form with:
      | name    |                          |
      | email   |          |
      | subject |            |
      | message |             |
    And I submit the contact form
    Then I should see an error message
  @pass
  Scenario: PASS - Submit contact form with valid information
    When I fill in the contact form with:
      | name    | Test User Demo        |
      | email   | testdemo@example.com  |
      | subject | Product Inquiry       |
      | message | I have a question about your products. Can you help me? |
    And I submit the contact form
    Then I should see a success message
    And the success message should contain "Success"



