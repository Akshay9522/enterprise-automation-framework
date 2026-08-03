package com.eaf.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {

        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                throw new IllegalStateException(
                        "config.properties not found in classpath.");
            }

            properties.load(inputStream);

        } catch (IOException e) {

            throw new IllegalStateException(
                    "Unable to load config.properties", e);

        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);

    }
}