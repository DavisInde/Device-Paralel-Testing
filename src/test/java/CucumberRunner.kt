import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    glue = ["/"],
    features = ["src/test/resources/features"],
    plugin = ["json:build/cucumber1.json", "pretty"],
    stepNotifications = true,
    tags = "",
    publish = true
)
class CucumberRunner