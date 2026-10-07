package org.example.test.utils;

import org.example.test.models.Device;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to create Xml and then save it to respective file
 * */
@Configuration
public class XmlCreator {

    @Value("${suite.name}")
    private String testName;

    public XmlSuite createXmlSuite(int totalThread) {
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(testName);
        xmlSuite.setThreadCount(totalThread);
        xmlSuite.setParallel(XmlSuite.ParallelMode.TESTS);
        xmlSuite.setPreserveOrder(true);

        return xmlSuite;
    }

    public XmlTest createXmlTest(
            String testTag,
            XmlSuite xmlSuite,
            Device device
    ) {
        XmlTest test = new XmlTest(xmlSuite);

        test.setName("Test " + testTag + " on device " + device.udid);

        /* Cucumber exclusives */
        test.addParameter("cucumber.filter.tags", testTag);

        /* Device farm exclusives */
        test.addParameter("deviceName", device.name);
        test.addParameter("hostName", device.host);
        test.addParameter("deviceUdid", device.udid);
        test.addParameter("sdkVersion", device.sdk);
        test.addParameter("systemPort", String.valueOf(device.systemPort));
        test.addParameter("adbPort", String.valueOf(device.adbPort));
        return test ;
    }

    public void saveXml(XmlSuite xmlSuite, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            String xmlFile = xmlSuite.toXml();
            System.out.println(xmlFile);
            writer.write(xmlFile);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
