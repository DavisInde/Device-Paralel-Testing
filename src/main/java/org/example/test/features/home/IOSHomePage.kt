package org.example.test.features.home

import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.By

class IOSHomePage(driver: IOSDriver): HomePage(driver) {
    override val ORDER_METHOD: By = By.id("home.header.orderMethod")
}