Feature: Smoke Tests

  @smoke @ui
  Scenario: Homepage accessibility check
    Given I open the Wego Delivery homepage
    Then the page title should contain "WEGO24 Business"
    And the "Sign In" button should be visible

  @smoke @ui
  Scenario: Navigation to Login page
    Given I am on the homepage
    When I click on the "Sign In" button
    Then I should be redirected to the login screen

  @smoke @api
  Scenario: Core API endpoint is responsive
    Given the Wego API is available
    When I send a GET request to "/profile"
    Then the response status should be 401 or 200