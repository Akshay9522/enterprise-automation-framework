package com.eaf.tests;

import com.eaf.base.BaseTest;
import com.eaf.config.CredentialProvider;
import com.eaf.pages.DashboardPage;
import com.eaf.pages.LoginPage;
import com.eaf.testdata.LoginData;
import com.eaf.testdata.provider.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                CredentialProvider.getUsername()
        );

        loginPage.enterPassword(
                CredentialProvider.getPassword()
        );

        DashboardPage dashboardPage =
                loginPage.clickLogin();

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed after valid login"
        );
    }


    @Test(
            dataProvider = "invalidLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void verifyInvalidLogin(LoginData loginData) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                loginData.getUsername()
        );

        loginPage.enterPassword(
                loginData.getPassword()
        );

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Login error message is not displayed for test case: "
                        + loginData.getTestCase()
        );
    }
}