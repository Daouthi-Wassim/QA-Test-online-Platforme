Feature: Contact Form

  @Contact
  Scenario: Submit contact form with valid data
    Given I am on the homepage
    When I navigate to contact page
    And I fill contact form with name "John Doe", email "john@example.com", subject "Test", message "Hello"
    And I submit contact form
    Then I should see success message

  @Contact
  Scenario: Submit contact form with invalid data
    Given I am on the homepage
    When I navigate to contact page
    And I submit empty contact form
    Then I should see error message