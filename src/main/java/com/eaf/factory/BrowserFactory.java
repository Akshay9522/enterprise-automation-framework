package com.eaf.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {

    public static WebDriver initializeBrowser(String browser) {

        switch (browser.toLowerCase()) {

            case "chrome":
                return new ChromeDriver();

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser);
        }
    }
}