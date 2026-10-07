package step;

import org.example.test.MainApplication;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = { MainApplication.class })
public class TestContext {
}
