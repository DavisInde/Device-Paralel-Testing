package org.example.test.testdriver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.ios.IOSDriver
import org.example.test.base.Config
import org.example.test.models.Device
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URI

object DriverFactory {
    fun create(device: Device, config: Config): AppiumDriver {
        val url = URI.create(config.baseUrl + config.basePath).toURL()

        return if (device.platform.equals("ios", true)) {
            val caps = DesiredCapabilities()
            caps.setCapability("platformName", "iOS")
            caps.setCapability("appium:automationName", "XCUITest")
            caps.setCapability("appium:bundleId", config.iosBundleId)
            caps.setCapability("appium:udid", device.udid)
            caps.setCapability("appium:noReset", true)
            caps.setCapability("appium:showXcodeLog", true)
            if (device.wdaLocalPort > 0) caps.setCapability("appium:wdaLocalPort", device.wdaLocalPort)
            if (device.mjpegServerPort > 0) caps.setCapability("appium:mjpegServerPort", device.mjpegServerPort)
            if (device.realDevice) {
                caps.setCapability("appium:useNewWDA", false)
                caps.setCapability("appium:xcodeSigningId", config.xcodeSigningId)
                if (!config.xcodeOrgId.isNullOrBlank()) caps.setCapability("appium:xcodeOrgId", config.xcodeOrgId)
                if (!config.updatedWDABundleId.isNullOrBlank())
                    caps.setCapability("appium:updatedWDABundleId", config.updatedWDABundleId)
            }
            IOSDriver(url, caps)
        } else {
            val options = UiAutomator2Options()
            options.setCapability("platformName", "Android")
            options.setCapability("appium:automationName", "UiAutomator2")
            options.setCapability("appium:udid", device.udid)
            options.setCapability("appium:noReset", true)
            if (device.systemPort > 0) options.setCapability("appium:systemPort", device.systemPort)
            if (device.adbPort > 0) options.setCapability("appium:adbPort", device.adbPort)
            options.setCapability("appPackage", config.appPackageId)
            if (!config.appActivity.isNullOrBlank()) options.setCapability("appActivity", config.appActivity)
            AndroidDriver(url, options)
        }
    }
}
