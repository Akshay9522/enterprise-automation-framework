package com.eaf.base;

import com.eaf.config.ConfigReader;
import com.eaf.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        String browser = ConfigReader.getProperty("browser");

        boolean incognito = Boolean.parseBoolean(
                ConfigReader.getProperty("incognito"));

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless"));

        boolean disableNotifications = Boolean.parseBoolean(
                ConfigReader.getProperty("disableNotifications"));

        driver = BrowserFactory.initializeBrowser(
                browser,
                headless,
                incognito,
                disableNotifications);

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}