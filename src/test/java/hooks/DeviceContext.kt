package hooks

import io.appium.java_client.AppiumDriver
import org.example.test.models.Device

object DeviceContext {
    val device = ThreadLocal<Device>()
    val driver = ThreadLocal<AppiumDriver>()
}