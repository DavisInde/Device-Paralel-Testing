import io.cucumber.testng.AbstractTestNGCucumberTests
import io.cucumber.testng.CucumberOptions
import org.example.test.models.Device
import org.example.test.testdriver.AndroidTest
import org.junit.BeforeClass
import org.testng.annotations.Parameters

@CucumberOptions(glue = ["/"], features = ["src/test/resources/features"], tags = "")
class MainRunner : AbstractTestNGCucumberTests() {
      @BeforeClass
      @Parameters("deviceName", "hostName", "deviceUdid", "sdkVersion", "systemPort", "adbPort", "platform")
      fun setupDevice(
              deviceName: String,
              hostName: String,
              deviceUdid: String,
              sdkVersion: String,
              systemPort: String,
              adbPort: String,
              platform: String
      ) {
        AndroidTest.device = Device(
            deviceName,
            hostName,
            deviceUdid,
            sdkVersion,
            Integer.parseInt(systemPort),
            Integer.parseInt(adbPort),
            platform
        );
      }
}
