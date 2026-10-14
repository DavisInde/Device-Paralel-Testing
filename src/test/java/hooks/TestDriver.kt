package hooks

import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import org.example.test.context.GlobalContext
import org.example.test.testdriver.AndroidTest
import org.example.test.testdriver.IOSTest
import org.example.test.utils.PlatformType
import org.example.test.utils.TestStatus
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

class TestDriver {
    private var iOSTest: IOSTest = IOSTest()
    private var androidTest: AndroidTest = AndroidTest()

    @Before
    fun beforeTest() {
        val device = GlobalContext.getCurrentDevice()
        when (PlatformType.getPlatformByName(device.platform ?: PlatformType.IOS.rawValue)) {
            PlatformType.IOS -> iOSTest.runTest(device)
            PlatformType.ANDROID -> androidTest.runTest(device)
            else -> throw RuntimeException("Unsupported apps platform")
        }
    }

    @After
    fun afterTest(scenario: Scenario) {
        val driver = DeviceContext.driver.get()
        try {
            if (scenario.isFailed) println("FAILED on ${DeviceContext.device.get()?.name}: ${scenario.name}")
        } finally {
            driver?.quit()
            DeviceContext.driver.remove()
        }
    }
}
