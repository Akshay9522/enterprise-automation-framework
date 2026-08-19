package com.eaf.testdata;

public class LoginData {

    private final String testCase;
    private final String username;
    private final String password;
    private final String expectedResult;

    public LoginData(
            String testCase,
            String username,
            String password,
            String expectedResult) {

        this.testCase = testCase;
        this.username = username;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    public String getTestCase() {
        return testCase;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}