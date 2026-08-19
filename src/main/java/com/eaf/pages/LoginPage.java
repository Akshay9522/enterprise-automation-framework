package com.eaf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String usernameValue) {
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(usernameValue);
    }

    public void enterPassword(String passwordValue) {
        wait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(passwordValue);
    }

    public DashboardPage clickLogin() {

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();

        return new DashboardPage(driver);
    }

    public boolean isLoginErrorDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(loginErrorMessage)
        ).isDisplayed();
    }
}