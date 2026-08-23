package com.eaf.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
        // Prevent object creation
    }

    public static byte[] captureAsBytes(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver cannot be null"
            );
        }

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}