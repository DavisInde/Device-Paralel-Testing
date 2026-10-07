import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com/apo/android/automation/apo"},
        features = {"src/test/resources/features"},
        plugin = {"json:build/cucumber1.json", "pretty"},
        stepNotifications = true,
        tags = "",
        publish = true
)
public class CucumberRunner {

}