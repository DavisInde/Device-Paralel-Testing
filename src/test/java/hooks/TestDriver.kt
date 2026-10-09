package hooks

import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import org.example.test.base.Config
import org.example.test.testdriver.DriverFactory

class TestDriver {
    @Before
    fun beforeTest() {
        val driver = DriverFactory.create(DeviceContext.device.get(), Config())
        DeviceContext.driver.set(driver)
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
