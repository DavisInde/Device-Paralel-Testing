package hooks

import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import org.example.test.testdriver.AndroidTest
import org.example.test.utils.TestStatus
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.Map

class TestDriver {
    @Before
    fun beforeTest() {
    }

    @After
    fun afterTest(scenario: Scenario) {
        AndroidTest.driver?.executeScript(
            "devicefarm: setSessionStatus",
            Map.of<String?, String?>("status", TestStatus.getTestStatus(!scenario.isFailed()))
        )
    }

    @SpringBootTest
    class TestApplication {
        @Test
        fun contextLoads() {
        }
    }
}
