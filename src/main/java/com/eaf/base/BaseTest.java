package com.eaf.base;

import com.eaf.config.BrowserConfig;
import com.eaf.config.ConfigReader;
import com.eaf.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        BrowserConfig browserConfig = new BrowserConfig(
                ConfigReader.getProperty("browser"),
               Boolean.parseBoolean(ConfigReader.getProperty("headless")),
                Boolean.parseBoolean(ConfigReader.getProperty("incognito")),
                Boolean.parseBoolean(ConfigReader.getProperty("disableNotifications"))
        );

       driver = BrowserFactory.initializeBrowser(browserConfig);
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