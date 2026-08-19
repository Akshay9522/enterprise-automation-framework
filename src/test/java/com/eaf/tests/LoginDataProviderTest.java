package com.eaf.tests;

import com.eaf.testdata.LoginData;
import com.eaf.testdata.provider.LoginDataProvider;
import org.testng.annotations.Test;

public class LoginDataProviderTest {

    @Test(dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class)
    public void verifyLoginData(LoginData loginData) {

        System.out.println(
                "Test Case: " + loginData.getTestCase()
        );

        System.out.println(
                "Username: " + loginData.getUsername()
        );

        System.out.println(
                "Password: " + loginData.getPassword()
        );

        System.out.println(
                "Expected Result: " + loginData.getExpectedResult()
        );
    }
}