package org.example.test.base

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class BaseTestPage protected constructor(protected open var driver: AppiumDriver) {
    fun findView(viewId: By): WebElement? {
        return driver.findElement(viewId)
    }

    fun waitUntilPresent(locator: By?) {
        val wait = WebDriverWait(driver, Duration.ofSeconds(15))
        wait.until<WebElement?>(ExpectedConditions.visibilityOfElementLocated(locator))
    }
}
