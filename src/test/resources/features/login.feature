Feature: Login functionality

  As a user
  I want to login to the application
  So that I can access the dashboard

  @smoke
  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters valid credentials
    And the user clicks the login button
    Then the dashboard should be displayed