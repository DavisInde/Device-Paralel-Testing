package org.example.test.context

import io.appium.java_client.AppiumDriver
import org.example.test.models.Device

object GlobalContext {
    private val device: ThreadLocal<Device?> = ThreadLocal()
    private val driver: ThreadLocal<AppiumDriver?> = ThreadLocal()

    fun setDevice(newDevice: Device) {
        device.set(newDevice)
    }

    fun getCurrentDevice(): Device {
        return device.get() ?: throw NullPointerException("Device belum diset")
    }

    fun setAppiumDriver(newAppiumDriver: AppiumDriver) {
        driver.set(newAppiumDriver)
    }

    fun getAppiumDriver(): AppiumDriver {
        return driver.get() ?: throw NullPointerException("Appium Driver belum diset")
    }

    fun removeAppiumDriver() {
        driver.remove()
    }
}