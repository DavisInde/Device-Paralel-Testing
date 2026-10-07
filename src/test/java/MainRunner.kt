import io.cucumber.testng.AbstractTestNGCucumberTests
import io.cucumber.testng.CucumberOptions

@CucumberOptions(glue = ["/"], features = ["src/test/resources/features"], tags = "@login")
class MainRunner : AbstractTestNGCucumberTests()
