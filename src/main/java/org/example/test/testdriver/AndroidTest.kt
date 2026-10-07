package org.example.test.testdriver

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.example.test.base.Config
import org.example.test.base.ConfigConsumer
import org.example.test.models.Device
import org.springframework.context.annotation.Configuration
import java.net.MalformedURLException
import java.net.URI

@Configuration
class AndroidTest : ConfigConsumer(Config()) {
    fun runTest(device: Device) {
        val options = UiAutomator2Options()
            .setAppPackage(config.appPackageId)

        options.setCapability("platformName", "Android")
        options.setCapability("appium:udid", device.udid)
        options.setCapability("appium:systemPort", device.systemPort)
        options.setCapability("appium:adbPort", device.adbPort)
        options.setCapability("appPackage", config.appPackageId)
        options.setCapability("appActivity", config.appActivity)
        options.setCapability("mobile: startActivity", config.appActivity)

        try {
            driver = AndroidDriver(
                URI.create(this.config.baseUrl + "wd/hub").toURL(),
                options
            )
        } catch (e1: MalformedURLException) {
            println("Yahh " + e1)
            println("Yahh " + e1.message)
        }
    }

    companion object {
        var device: Device? = null

        var driver: AndroidDriver? = null
    }
}
