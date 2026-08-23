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

    }
}