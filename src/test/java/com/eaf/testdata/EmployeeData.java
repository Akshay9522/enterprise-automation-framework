package com.eaf.testdata;

public class EmployeeData {

    private final String testCase;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String employeeId;
    private final String expectedResult;

    public EmployeeData(
            String testCase,
            String firstName,
            String middleName,
            String lastName,
            String employeeId,
            String expectedResult) {

        this.testCase = testCase;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.expectedResult = expectedResult;
    }

    public String getTestCase() {
        return testCase;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}