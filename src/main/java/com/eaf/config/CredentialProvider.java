package com.eaf.config;

public final class CredentialProvider {

    private static final String USERNAME_ENV = "EAF_USERNAME";
    private static final String PASSWORD_ENV = "EAF_PASSWORD";

    private CredentialProvider() {
        // Prevent object creation
    }

    public static String getUsername() {
        return getRequiredEnvironmentVariable(USERNAME_ENV);
    }

    public static String getPassword() {
        return getRequiredEnvironmentVariable(PASSWORD_ENV);
    }

    private static String getRequiredEnvironmentVariable(String variableName) {

        String value = System.getenv(variableName);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Required environment variable is not configured: "
                            + variableName
            );
        }

        return value;
    }
}