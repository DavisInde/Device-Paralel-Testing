import hooks.DeviceContext
import io.cucumber.tagexpressions.TagExpressionParser
import io.cucumber.testng.AbstractTestNGCucumberTests
import io.cucumber.testng.CucumberOptions
import org.example.test.context.GlobalContext
import org.example.test.models.Device
import org.testng.annotations.BeforeClass
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
        println("VIS LOG HAHA")
        val device = Device(
            deviceName,
            hostName,
            deviceUdid,
            sdkVersion,
            Integer.parseInt(systemPort),
            Integer.parseInt(adbPort),
            platform
        )

        GlobalContext.setDevice(device)
    }
}
