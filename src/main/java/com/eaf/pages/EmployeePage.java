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

    @FindBy(className = "oxd-form-loader")
    private WebElement formLoader;

    public EmployeePage(WebDriver driver) {
        super(driver);
    }


    public void openPIM() {
        click(pimMenu);
    }

    public void clickAddEmployee() {
        click(addEmployee);
    }

    public void enterFirstName(String firstNameValue) {
        enterText(firstName, firstNameValue);
    }

    public void enterMiddleName(String middleNameValue) {
        enterText(middleName, middleNameValue);
    }

    public void enterLastName(String lastNameValue) {
        enterText(lastName, lastNameValue);
    }

    public void enterEmployeeId(String employeeIdValue) {
        enterText(employeeId, employeeIdValue);
    }

    public void clickSave() {

        // Application-specific synchronization:
        // wait until OrangeHRM form loader disappears
        wait.until(
                ExpectedConditions.invisibilityOf(formLoader)
        );

        // Generic Selenium action from BasePage
        click(saveButton);
    }

    public boolean isPersonalDetailsDisplayed() {
        return isDisplayed(personalDetailsHeader);
    }
}