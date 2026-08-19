package com.eaf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmployeePage extends BasePage {

    // PIM menu
    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    // Add Employee button
    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployee;

    // Employee form fields
    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "middleName")
    private WebElement middleName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    private WebElement employeeId;

    // Save button
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    // Employee details page verification
    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;


    public EmployeePage(WebDriver driver) {
        super(driver);
    }


    public void openPIM() {

        wait.until(
                ExpectedConditions.elementToBeClickable(pimMenu)
        );

        pimMenu.click();
    }


    public void clickAddEmployee() {

        wait.until(
                ExpectedConditions.elementToBeClickable(addEmployee)
        );

        addEmployee.click();
    }


    public void enterFirstName(String firstNameValue) {

        wait.until(
                ExpectedConditions.visibilityOf(firstName)
        );

        firstName.clear();
        firstName.sendKeys(firstNameValue);
    }


    public void enterMiddleName(String middleNameValue) {

        wait.until(
                ExpectedConditions.visibilityOf(middleName)
        );

        middleName.clear();
        middleName.sendKeys(middleNameValue);
    }


    public void enterLastName(String lastNameValue) {

        wait.until(
                ExpectedConditions.visibilityOf(lastName)
        );

        lastName.clear();
        lastName.sendKeys(lastNameValue);
    }


    public void enterEmployeeId(String employeeIdValue) {

        wait.until(
                ExpectedConditions.visibilityOf(employeeId)
        );

        employeeId.clear();
        employeeId.sendKeys(employeeIdValue);
    }


    public void clickSave() {

        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        );

        saveButton.click();
    }


    public boolean isPersonalDetailsDisplayed() {

        wait.until(
                ExpectedConditions.visibilityOf(personalDetailsHeader)
        );

        return personalDetailsHeader.isDisplayed();
    }
}