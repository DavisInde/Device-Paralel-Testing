package org.example.test.base

import io.appium.java_client.AppiumDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class BaseTestPage protected constructor(protected open var driver: AppiumDriver) {
    fun findView(viewId: By): WebElement? {
        return try {
            driver.findElement(viewId)
        } catch (e: Exception) {
            null
        }
    }

    fun tap(locator: By) {
        waitUntilPresent(locator)
        driver.findElement(locator).click()
    }

    /**
     * Sends [taps] taps as a SINGLE multi-tap gesture, so the OS recognises them as one
     * `onTapGesture(count:)`. Repeated [tap] calls are seconds apart (one HTTP round trip each)
     * and the OS counts them as separate single taps, so they never add up.
     */
    fun multiTap(locator: By, taps: Int) {
        waitUntilPresent(locator)
        val element = driver.findElement(locator) as RemoteWebElement
        when (val currentDriver = driver) {
            is IOSDriver -> currentDriver.executeScript(
                "mobile: tapWithNumberOfTaps",
                mapOf(
                    "elementId" to element.id,
                    "numberOfTaps" to taps,
                    "numberOfTouches" to 1
                )
            )

            else -> throw UnsupportedOperationException(
                "multiTap is only implemented for iOS, got ${currentDriver.javaClass.simpleName}"
            )
        }
    }

    fun typeText(locator: By, text: String) {
        waitUntilPresent(locator)
        driver.findElement(locator).sendKeys(text)
    }

    fun isVisible(locator: By): Boolean {
        return try {
            driver.findElement(locator).isDisplayed
        } catch (e: Exception) {
            false
        }
    }

    /** Like [isVisible], but gives the UI time to settle instead of judging on the first poll. */
    fun becomesVisible(locator: By, timeout: Duration = Duration.ofSeconds(10)): Boolean {
        return try {
            waitUntilPresent(locator, timeout)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun waitUntilPresent(locator: By?, timeout: Duration = Duration.ofSeconds(15)) {
        val wait = WebDriverWait(driver, timeout)
        wait.until<WebElement?>(ExpectedConditions.visibilityOfElementLocated(locator))
    }
}
