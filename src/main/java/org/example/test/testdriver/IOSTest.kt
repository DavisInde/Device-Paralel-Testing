package org.example.test.testdriver

import io.appium.java_client.ios.IOSDriver
import org.example.test.base.Config
import org.example.test.base.ConfigConsumer
import org.example.test.models.Device
import org.example.test.utils.TestStatus.Companion.getTestStatus
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.MalformedURLException
import java.net.URI
import java.util.Map

class IOSTest(config: Config) : ConfigConsumer(config) {
    var driver: IOSDriver? = null

    fun runTest(device: Device): Boolean {
        var testStatus = false

        val caps = DesiredCapabilities()
        caps.setCapability("platformName", "iOS")
        caps.setCapability("appium:automationName", "XCUITest")
        caps.setCapability("appium:bundleId", config.appPackageId)
        caps.setCapability("appium:udid", device.udid)
        caps.setCapability("appium:wdaLocalPort", device.wdaLocalPort)

        try {
            driver = IOSDriver(
                URI.create(this.config.baseUrl + "wd/hub").toURL(),
                caps
            )

            testStatus = true
        } catch (e1: MalformedURLException) {
            println("Yahh " + e1)
        } finally {
            driver!!.executeScript(
                "devicefarm: setSessionStatus",
                Map.of<String?, String?>("status", getTestStatus(testStatus))
            )
        }

        return testStatus
    }
}
