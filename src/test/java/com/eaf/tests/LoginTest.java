package com.eaf.tests;

import com.eaf.base.BaseTest;
import com.eaf.pages.DashboardPage;
import com.eaf.pages.LoginPage;
import com.eaf.testdata.LoginData;
import com.eaf.testdata.provider.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(
            dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void verifyLogin(LoginData loginData) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(loginData.getUsername());
        loginPage.enterPassword(loginData.getPassword());

        loginPage.clickLogin();

        if (loginData.getExpectedResult().equalsIgnoreCase("Success")) {

            DashboardPage dashboardPage = new DashboardPage(driver);

            Assert.assertTrue(
                    dashboardPage.isDashboardDisplayed(),
                    "Dashboard page is not displayed after successful login"
            );

        } else if (loginData.getExpectedResult().equalsIgnoreCase("Failure")) {

            Assert.assertTrue(
                    loginPage.isLoginErrorDisplayed(),
                    "Login error message is not displayed for invalid credentials"
            );

        } else {

            Assert.fail(
                    "Unsupported ExpectedResult in Excel: "
                            + loginData.getExpectedResult()
            );
        }
    }
}