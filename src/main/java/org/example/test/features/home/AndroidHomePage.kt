package org.example.test.features.home

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class AndroidHomePage(driver: AndroidDriver): HomePage(driver) {
    override val ORDER_METHOD: By = By.id("tv_order_method")
}