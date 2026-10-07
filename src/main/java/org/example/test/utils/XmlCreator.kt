package org.example.test.utils

import org.example.test.models.Device
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.testng.xml.XmlSuite
import org.testng.xml.XmlTest
import java.io.FileWriter
import java.io.IOException

/**
 * Class to create Xml and then save it to respective file
 */
@Configuration
class XmlCreator {
    @Value("\${suite.name}")
    private val testName: String? = null

    fun createXmlSuite(totalThread: Int): XmlSuite {
        val xmlSuite = XmlSuite()
        xmlSuite.setName(testName)
        xmlSuite.setThreadCount(totalThread)
        xmlSuite.setParallel(XmlSuite.ParallelMode.TESTS)
        xmlSuite.setPreserveOrder(true)

        return xmlSuite
    }

    fun createXmlTest(
        testTag: String?,
        xmlSuite: XmlSuite?,
        device: Device
    ): XmlTest {
        val test = XmlTest(xmlSuite)

        test.setName("Test " + testTag + " on device " + device.udid)

        /* Cucumber exclusives */
        test.addParameter("cucumber.filter.tags", testTag)

        /* Device farm exclusives */
        test.addParameter("deviceName", device.name)
        test.addParameter("hostName", device.host)
        test.addParameter("deviceUdid", device.udid)
        test.addParameter("sdkVersion", device.sdk)
        test.addParameter("systemPort", device.systemPort.toString())
        test.addParameter("adbPort", device.adbPort.toString())
        return test
    }

    fun saveXml(xmlSuite: XmlSuite, path: String) {
        try {
            FileWriter(path).use { writer ->
                val xmlFile = xmlSuite.toXml()
                println(xmlFile)
                writer.write(xmlFile)
                writer.flush()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
