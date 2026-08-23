package com.eaf.hooks;

import com.eaf.driver.DriverManager;
import com.eaf.driver.DriverService;
import com.eaf.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

                byte[] screenshot =
                        ScreenshotUtil.captureAsBytes(
                                DriverManager.getDriver()
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