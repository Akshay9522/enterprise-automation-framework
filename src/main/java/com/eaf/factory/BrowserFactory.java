package com.eaf.factory;

import com.eaf.config.BrowserConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver initializeBrowser(BrowserConfig config) {

        switch (config.getBrowser().toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                if (config.isHeadless()) {
                    chromeOptions.addArguments("--headless");
                }

                if (config.isIncognito()) {
                    chromeOptions.addArguments("--incognito");
                }

                if (config.isDisableNotifications()) {
                    chromeOptions.addArguments("--disable-notifications");
                }

                return new ChromeDriver(chromeOptions);

            case "firefox":

                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (config.isHeadless()) {
                    firefoxOptions.addArguments("--headless");
                }

                if (config.isIncognito()) {
                    firefoxOptions.addArguments("-private");
                }

                if (config.isDisableNotifications()) {
                    firefoxOptions.addPreference(
                            "dom.webnotifications.enabled", false);
                }

                return new FirefoxDriver(firefoxOptions);

            case "edge":

                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();

                if (config.isHeadless()) {
                    edgeOptions.addArguments("--headless");
                }

                if (config.isIncognito()) {
                    edgeOptions.addArguments("--inprivate");
                }

                if (config.isDisableNotifications()) {
                    edgeOptions.addArguments("--disable-notifications");
                }

                return new EdgeDriver(edgeOptions);

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: " + config.getBrowser());
        }
    }
}