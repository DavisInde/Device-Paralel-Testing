package org.example.test.base;

import org.springframework.stereotype.Component;

/**
 * @field baseUrl This is the base url for appium server
 * @field appPackageId This is bundle id or app package name
 * @field appActivity Main activity to be launched in android
 * @field platform either android/ iOS to test each one
 * */
@Component
public class Config {
    public String baseUrl;
    public String appPackageId;
    public String appActivity;
    public String platform;

    //TODO: change baseUrl if not using docker
    public Config() {
        this.baseUrl = "http://localhost:4723/";
        this.appPackageId = "";
        this.appActivity = "";
        this.platform = "android";
    }

    public Config(String baseUrl, String appPackageId, String appActivity, String platform) {
        this.baseUrl = baseUrl;
        this.appPackageId = appPackageId;
        this.appActivity = appActivity;
        this.platform = platform;
    }
}