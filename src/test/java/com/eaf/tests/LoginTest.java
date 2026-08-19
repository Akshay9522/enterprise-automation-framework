package com.eaf.tests;

import com.eaf.base.BaseTest;
import com.eaf.pages.DashboardPage;
import com.eaf.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");

        DashboardPage dashboardPage = loginPage.clickLogin();

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard page is not displayed after successful login"
        );
    }
}