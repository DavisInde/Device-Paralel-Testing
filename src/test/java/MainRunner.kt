import hooks.DeviceContext
import io.cucumber.tagexpressions.TagExpressionParser
import io.cucumber.testng.AbstractTestNGCucumberTests
import io.cucumber.testng.CucumberOptions
import io.cucumber.testng.PickleWrapper
import org.example.test.models.Device
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Optional
import org.testng.annotations.Parameters

@CucumberOptions(glue = ["/"], features = ["src/test/resources/features"])
class MainRunner : AbstractTestNGCucumberTests() {
    private var tagFilter: String = ""

    @BeforeClass(alwaysRun = true)
    @Parameters("deviceName", "deviceUdid", "wdaLocalPort", "systemPort", "adbPort", "platform", "cucumber.filter.tags")
    fun setDevice(
        name: String?, udid: String?,
        wdaPort: String,
        systemPort: String,
        adbPort: String,
        platform: String?,
        @Optional("") tags: String
    ) {
        val device = Device()
        device.name = name
        device.udid = udid
        device.wdaLocalPort = wdaPort.toInt()
        device.systemPort = systemPort.toInt()
        device.adbPort = adbPort.toInt()
        device.platform = platform
        DeviceContext.device.set(device)
        tagFilter = tags
    }

    /** Per-device tag filter. Cucumber's own tag property is JVM-global, so filter here instead. */
    @DataProvider(name = "scenarios", parallel = false)
    override fun scenarios(): Array<Array<Any>> {
        val all = super.scenarios()
        if (tagFilter.isBlank()) return all
        val expr = TagExpressionParser.parse(tagFilter)
        return all.filter { row ->
            val pickle = (row[0] as PickleWrapper).pickle
            expr.evaluate(pickle.tags)
        }.toTypedArray()
    }
}
