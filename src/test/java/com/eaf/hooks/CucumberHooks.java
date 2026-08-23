package com.eaf.hooks;

import com.eaf.driver.DriverManager;
import com.eaf.driver.DriverService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CucumberHooks {

    private static final Logger logger =
            LogManager.getLogger(CucumberHooks.class);

    @Before
    public void setUp(Scenario scenario) {

        logger.info(
                "Starting Cucumber scenario: {}",
                scenario.getName()
        );

        DriverService.startDriver();

        logger.info(
                "Browser initialized for Cucumber scenario"
        );
    }

    @After
    public void tearDown(Scenario scenario) {

        try {

            if (scenario.isFailed()) {

                logger.error(
                        "Cucumber scenario failed: {}",
                        scenario.getName()
                );

                WebDriver driver =
                        DriverManager.getDriver();

                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(
                                        OutputType.BYTES
                                );

                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );

                logger.info(
                        "Failure screenshot attached for scenario: {}",
                        scenario.getName()
                );
            }

        } finally {

            DriverService.quitDriver();

            logger.info(
                    "Finished Cucumber scenario: {}",
                    scenario.getName()
            );
        }
    }
}