Feature: Employee management

  @smoke
  Scenario: Create a new employee successfully
    Given the user is logged in
    When the user opens the PIM module
    And the user opens Add Employee
    And the user enters valid employee details
    And the user saves the employee
    Then the personal details page should be displayed