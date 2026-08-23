package com.eaf.stepdefinitions;

import com.eaf.config.CredentialProvider;
import com.eaf.driver.DriverManager;
import com.eaf.pages.EmployeePage;
import com.eaf.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EmployeeSteps {

    private EmployeePage employeePage;

    @Given("the user is logged in")
    public void userIsLoggedIn() {

        LoginPage loginPage =
                new LoginPage(
                        DriverManager.getDriver()
                );

        loginPage.enterUsername(
                CredentialProvider.getUsername()
        );

        loginPage.enterPassword(
                CredentialProvider.getPassword()
        );

        loginPage.clickLogin();

        employeePage =
                new EmployeePage(
                        DriverManager.getDriver()
                );
    }

    @When("the user opens the PIM module")
    public void userOpensPIMModule() {

        employeePage.openPIM();
    }

    @When("the user opens Add Employee")
    public void userOpensAddEmployee() {

        employeePage.clickAddEmployee();
    }

    @When("the user enters valid employee details")
    public void userEntersValidEmployeeDetails() {

        employeePage.enterFirstName("Cucumber");
        employeePage.enterMiddleName("BDD");
        employeePage.enterLastName("User");
        employeePage.enterEmployeeId("CUC001");
    }

    @When("the user saves the employee")
    public void userSavesEmployee() {

        employeePage.clickSave();
    }

    @Then("the personal details page should be displayed")
    public void personalDetailsPageShouldBeDisplayed() {

        Assert.assertTrue(
                employeePage.isPersonalDetailsDisplayed(),
                "Personal Details page is not displayed"
        );
    }
}