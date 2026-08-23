package com.eaf.pages;

import com.eaf.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("explicitWait"))
                            )
        );

        PageFactory.initElements(driver, this);
    }
    protected void click(WebElement element) {

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        ).click();
    }

    protected void enterText(WebElement element, String text) {

        wait.until(
                ExpectedConditions.visibilityOf(element)
        );

        element.clear();
        element.sendKeys(text);
    }

    protected boolean isDisplayed(WebElement element) {

        return wait.until(
                ExpectedConditions.visibilityOf(element)
        ).isDisplayed();
    }

}