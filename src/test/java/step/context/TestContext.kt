package step.context

import io.cucumber.spring.CucumberContextConfiguration
import org.example.test.MainApplication
import org.springframework.boot.test.context.SpringBootTest

@CucumberContextConfiguration
@SpringBootTest(classes = [MainApplication::class])
class TestContext
