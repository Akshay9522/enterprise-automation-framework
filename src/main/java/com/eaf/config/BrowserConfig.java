package com.eaf.config;

public class BrowserConfig {

    private final String browser;
    private final boolean headless;
    private final boolean incognito;
    private final boolean disableNotifications;

    public BrowserConfig(
            String browser,
            boolean headless,
            boolean incognito,
            boolean disableNotifications){

        this.browser = browser;
        this.incognito = incognito;
        this.headless = headless;
        this.disableNotifications = disableNotifications;
    }

    public String getBrowser(){
        return browser;
    }

    public boolean isHeadless() {
        return headless;
    }

    public boolean isIncognito() {
        return incognito;
    }

    public boolean isDisableNotifications() {
        return disableNotifications;
    }
}
