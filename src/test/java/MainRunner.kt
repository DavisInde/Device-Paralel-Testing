import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.example.test.models.Device;
import org.example.test.testdriver.AndroidTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        glue = { "/" },
        features = { "src/test/resources/features" },
        tags = "@login"
)
public class MainRunner extends AbstractTestNGCucumberTests {
//  @BeforeClass
//  @Parameters({ "deviceName", "hostName", "deviceUdid", "sdkVersion", "systemPort", "adbPort" })
//  public void setupDevice(
//          String deviceName,
//          String hostName,
//          String deviceUdid,
//          String sdkVersion,
//          String systemPort,
//          String adbPort
//  ) {
//    AndroidTest.device = new Device(
//            deviceName,
//            hostName,
//            deviceUdid,
//            sdkVersion,
//            Integer.parseInt(systemPort),
//            Integer.parseInt(adbPort)
//    );
//  }
}
