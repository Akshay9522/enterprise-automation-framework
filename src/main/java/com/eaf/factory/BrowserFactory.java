package com.eaf.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver initializeBrowser(String browser, boolean headless, boolean incognito, boolean disableNotifications) {

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless");
                }

                if (incognito) {
                    chromeOptions.addArguments("--incognito");
                }

                if (disableNotifications) {
                    chromeOptions.addArguments("--disable-notifications");
                }

                return new ChromeDriver(chromeOptions);

            case "firefox":

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }

                if (incognito) {
                    firefoxOptions.addArguments("-private");
                }

                if (disableNotifications) {
                    firefoxOptions.addPreference(
                            "dom.webnotifications.enabled", false);
                }

                return new FirefoxDriver(firefoxOptions);

            case "edge":

                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();

                if (headless) {
                    edgeOptions.addArguments("--headless");
                }

                if (incognito) {
                    edgeOptions.addArguments("--inprivate");
                }

                if (disableNotifications) {
                    edgeOptions.addArguments("--disable-notifications");
                }

                return new EdgeDriver(edgeOptions);

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser);
        }
    }
}