package com.eaf.listeners;

import com.eaf.base.BaseTest;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    private static final Logger logger =
            LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        logger.error(
                "Test failed: {}",
                testName
        );

        Object testInstance =
                result.getInstance();

        if (!(testInstance instanceof BaseTest)) {

            logger.warn(
                    "Unable to capture screenshot because test does not extend BaseTest: {}",
                    testName
            );

            return;
        }

        BaseTest baseTest =
                (BaseTest) testInstance;

        WebDriver driver =
                baseTest.getDriver();

        if (driver == null) {

            logger.warn(
                    "Unable to capture screenshot because WebDriver is null for test: {}",
                    testName
            );

            return;
        }

        File screenshot =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        Path screenshotDirectory =
                Path.of(
                        "target",
                        "screenshots"
                );

        Path destination =
                screenshotDirectory.resolve(
                        testName + ".png"
                );

        try {

            Files.createDirectories(
                    screenshotDirectory
            );

            Files.copy(
                    screenshot.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            logger.info(
                    "Failure screenshot saved to: {}",
                    destination
            );

            try (FileInputStream inputStream =
                         new FileInputStream(
                                 destination.toFile()
                         )) {

                Allure.addAttachment(
                        "Failure Screenshot",
                        "image/png",
                        inputStream,
                        ".png"
                );
            }

            logger.info(
                    "Failure screenshot attached to Allure for test: {}",
                    testName
            );

        } catch (IOException e) {

            logger.error(
                    "Unable to save or attach screenshot for failed test: {}",
                    testName,
                    e
            );

            throw new IllegalStateException(
                    "Unable to save or attach screenshot for failed test: "
                            + testName,
                    e
            );
        }
    }
}