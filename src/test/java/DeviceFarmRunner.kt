import org.example.test.MainApplication
import org.example.test.models.Device
import org.example.test.service.DeviceFarmService
import org.example.test.utils.XmlCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.testng.xml.XmlClass

@SpringBootTest(classes = [MainApplication::class])
class DeviceFarmRunner : AbstractTestNGSpringContextTests() {
    private var availableDevices: Array<Device?> = arrayOfNulls<Device?>(0)

    @Autowired
    private val service: DeviceFarmService? = null

    @Autowired
    private val factory: XmlCreator? = null

    @Value("\${suite.file.path}")
    private val filePath: String? = null

    @BeforeClass
    fun getConnectedDevices() {
        val platform = prop("platform").lowercase().ifBlank { "both" }
        availableDevices = service!!.availableDevices
            .filter { it != null && it.realDevice && !it.offline && !it.busy && !it.userBlocked }
            .filter { platform == "both" || it!!.platform.equals(platform, true) }
            .toTypedArray()
        println("Real devices ($platform): " + availableDevices.joinToString { "${it?.platform}:${it?.name}:${it?.udid}" })
    }

    @Test
    fun runTest() {
        val xmlSuite = factory!!.createXmlSuite(availableDevices.size)
        for (device in availableDevices) {
            val tag = tagFor(device!!)
            println("${device.platform}:${device.name} -> tags '${tag}'")
            val test = factory.createXmlTest(tag, xmlSuite, device)
            val xmlClass = XmlClass()
            xmlClass.name = "MainRunner"
            test.xmlClasses = listOf<XmlClass?>(xmlClass)
        }
        factory.saveXml(xmlSuite, filePath!!)
    }

    /** Priority: per-device (udid) > per-platform > default -Ptags. Blank = run all scenarios */
    private fun tagFor(device: Device): String {
        val perDevice = prop("deviceTags").split(";")
            .mapNotNull { it.split("=", limit = 2).takeIf { p -> p.size == 2 } }
            .associate { it[0].trim() to it[1].trim() }
        perDevice[device.udid]?.let { return it }
        val perPlatform = if (device.platform.equals("ios", true)) prop("tagsIos") else prop("tagsAndroid")
        return perPlatform.ifBlank { prop("tags") }
    }

    private fun prop(key: String) = System.getProperty("run.$key").orEmpty().trim()
}
