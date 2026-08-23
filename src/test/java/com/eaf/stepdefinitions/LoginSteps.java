package com.eaf.stepdefinitions;

import com.eaf.config.CredentialProvider;
import com.eaf.driver.DriverManager;
import com.eaf.pages.DashboardPage;
import com.eaf.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("the user is on the login page")
    public void userIsOnLoginPage() {

        loginPage =
                new LoginPage(
                        DriverManager.getDriver()
                );
    }

    @When("the user enters valid credentials")
    public void userEntersValidCredentials() {

        loginPage.enterUsername(
                CredentialProvider.getUsername()
        );

        loginPage.enterPassword(
                CredentialProvider.getPassword()
        );
    }

    @When("the user clicks the login button")
    public void userClicksLoginButton() {

        dashboardPage =
                loginPage.clickLogin();
    }

    @Then("the dashboard should be displayed")
    public void dashboardShouldBeDisplayed() {

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed after valid login"
        );
    }
}