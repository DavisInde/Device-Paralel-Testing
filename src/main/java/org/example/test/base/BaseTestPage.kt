package org.example.test.base;

import io.appium.java_client.AppiumDriver;

public abstract class BaseTestPage {

    AppiumDriver driver ;

    protected BaseTestPage(AppiumDriver driver) {
        this.driver = driver;
    }
}
