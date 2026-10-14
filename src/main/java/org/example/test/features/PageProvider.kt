package org.example.test.features

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.example.test.base.BaseTestPage

object PageProvider {
    fun <page: BaseTestPage> getPage(
        driver: AppiumDriver,
        android: (AndroidDriver) -> page,
        iOS: (IOSDriver) -> page
    ): page {
        return when (driver) {
            is AndroidDriver -> android(driver)
            is IOSDriver -> iOS(driver)
            else -> throw IllegalArgumentException("No compatible driver")
        }
    }
}