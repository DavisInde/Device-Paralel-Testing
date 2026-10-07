import org.example.test.MainApplication
import org.example.test.models.Device
import org.example.test.service.DeviceFarmService
import org.example.test.utils.XmlCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.annotations.BeforeClass
import org.testng.annotations.Optional
import org.testng.annotations.Parameters
import org.testng.annotations.Test
import org.testng.xml.XmlClass
import java.util.*
import java.util.List

@SpringBootTest(classes = [MainApplication::class])
class DeviceFarmRunner : AbstractTestNGSpringContextTests() {
    private var availableDevices: Array<Device?> = arrayOfNulls<Device?>(0)
    private var scenarioTags = arrayOfNulls<String>(0)

    @Autowired
    private val service: DeviceFarmService? = null

    @Autowired
    private val factory: XmlCreator? = null

    @Value("\${suite.file.path}")
    private val filePath: String? = null

    @BeforeClass
    @Parameters("cucumber.filter.tags")
    fun getConnectedDevice(@Optional(value = "cucumber.filter.tags") parameterizeTag: String?) {
        val tag = getScenario(parameterizeTag)
        scenarioTags = tag.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        Arrays.stream<String?>(scenarioTags).forEach { x: String? -> println(x) }
        availableDevices = service!!.availableDevices
    }

    @Test
    fun runTest() {
        generateXml(scenarioTags)
    }

    private fun generateXml(tags: Array<String?>) {
        val totalDevice = availableDevices.size
        val totalTest = tags.size

        val totalThread = totalTest //Or device

        val xmlSuite = factory!!.createXmlSuite(totalDevice)
        for (i in 0..<totalThread) {
            val tag = tags[i]

            if (i >= totalDevice) break

            val assignedDevice = availableDevices[i]

            val test = factory.createXmlTest(tag, xmlSuite, assignedDevice!!)

            val xmlClass = XmlClass()
            xmlClass.setName("MainRunner")

            test.setXmlClasses(List.of<XmlClass?>(xmlClass))
        }
        factory.saveXml(xmlSuite, filePath!!)
    }

    private fun getScenario(parameterizedTag: String?): String {
        return if (parameterizedTag == null) System.getProperty("cucumber.filter.tags") else parameterizedTag
    }
}
