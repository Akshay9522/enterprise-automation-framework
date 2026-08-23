package com.eaf.driver;

import com.eaf.config.BrowserConfig;
import com.eaf.config.ConfigReader;
import com.eaf.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;

public final class DriverService {

    private DriverService() {
        // Prevent object creation
    }

    public static void startDriver() {

        BrowserConfig config =
                new BrowserConfig(
                        ConfigReader.getProperty("browser"),
                        Boolean.parseBoolean(
                                ConfigReader.getProperty("headless")
                        ),
                        Boolean.parseBoolean(
                                ConfigReader.getProperty("incognito")
                        ),
                        Boolean.parseBoolean(
                                ConfigReader.getProperty(
                                        "disableNotifications"
                                )
                        )
                );

        WebDriver driver =
                BrowserFactory.initializeBrowser(config);

        DriverManager.setDriver(driver);

        driver.manage()
                .window()
                .maximize();

        driver.manage()
                .deleteAllCookies();

        driver.get(
                ConfigReader.getProperty("url")
        );
    }

    public static void quitDriver() {
        DriverManager.quitDriver();
    }
}