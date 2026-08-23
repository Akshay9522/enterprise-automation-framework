package com.eaf.base;

import com.eaf.driver.DriverManager;
import com.eaf.driver.DriverService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected final Logger logger =
            LogManager.getLogger(getClass());

    @BeforeMethod
    public void setUp() {

        logger.info("Starting test environment setup");

        DriverService.startDriver();

        logger.info("Browser initialized successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        logger.info("Starting test environment cleanup");

        DriverService.quitDriver();

        logger.info("Browser session closed");
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}