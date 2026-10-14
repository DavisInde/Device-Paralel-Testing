package org.example.test.features.home

import io.appium.java_client.AppiumDriver
import org.example.test.base.BaseTestPage
import org.openqa.selenium.By
import org.springframework.stereotype.Component

@Component
abstract class HomePage(driver: AppiumDriver): BaseTestPage(driver) {
    abstract val ORDER_METHOD : By

    fun testOrderMethodVisible() {
        waitUntilPresent(ORDER_METHOD)
    }
}