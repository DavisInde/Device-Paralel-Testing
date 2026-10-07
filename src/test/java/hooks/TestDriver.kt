package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.test.testdriver.AndroidTest;
import org.example.test.utils.TestStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

public class TestDriver {

  @Before
  public void beforeTest() {

  }

  @After
  public void afterTest(Scenario scenario) {
    AndroidTest.driver.executeScript(
            "devicefarm: setSessionStatus",
            Map.of("status", TestStatus.getTestStatus(!scenario.isFailed()))
    ) ;
  }

  @SpringBootTest
  public static class TestApplication {
    @Test
    void contextLoads() {
    }
  }
}
