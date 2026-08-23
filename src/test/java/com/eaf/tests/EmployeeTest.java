package com.eaf.tests;

import com.eaf.base.BaseTest;
import com.eaf.config.CredentialProvider;
import com.eaf.pages.EmployeePage;
import com.eaf.pages.LoginPage;
import com.eaf.testdata.EmployeeData;
import com.eaf.testdata.provider.EmployeeDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {

    @Test(
            dataProvider = "employeeData",
            dataProviderClass = EmployeeDataProvider.class
    )
    public void verifyEmployeeCreation(EmployeeData employeeData) {

        // Login
        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.enterUsername(
                CredentialProvider.getUsername()
        );

        loginPage.enterPassword(
                CredentialProvider.getPassword()
        );

        loginPage.clickLogin();

        // Employee
        EmployeePage employeePage = new EmployeePage(getDriver());

        employeePage.openPIM();
        employeePage.clickAddEmployee();

        employeePage.enterFirstName(
                employeeData.getFirstName()
        );

        employeePage.enterMiddleName(
                employeeData.getMiddleName()
        );

        employeePage.enterLastName(
                employeeData.getLastName()
        );

        employeePage.enterEmployeeId(
                employeeData.getEmployeeId()
        );

        employeePage.clickSave();

        // Verification
        if (employeeData.getExpectedResult()
                .equalsIgnoreCase("Success")) {

            Assert.assertTrue(
                    employeePage.isPersonalDetailsDisplayed(),
                    "Personal Details page is not displayed after employee creation"
            );

        } else {

            Assert.fail(
                    "Unsupported ExpectedResult in Excel: "
                            + employeeData.getExpectedResult()
            );
        }
    }
}