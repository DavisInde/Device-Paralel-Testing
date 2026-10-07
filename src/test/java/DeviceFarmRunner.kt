import org.example.test.MainApplication;
import org.example.test.models.Device;
import org.example.test.service.DeviceFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.example.test.utils.XmlCreator;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = { MainApplication.class })
public class DeviceFarmRunner extends AbstractTestNGSpringContextTests {
  private Device[] availableDevices = new Device[0] ;
  private String[] scenarioTags = new String[0];

  @Autowired
  private DeviceFarmService service;

  @Autowired
  private XmlCreator factory;

  @Value("${suite.file.path}")
  private String filePath ;

  @BeforeClass
  @Parameters({"cucumber.filter.tags"})
  public void getConnectedDevice(@Optional(value = "cucumber.filter.tags") String parameterizeTag) {
    String tag = getScenario(parameterizeTag);
    scenarioTags = tag.split("\\.");
    Arrays.stream(scenarioTags).forEach(System.out::println);
    availableDevices = service.getAvailableDevices();
  }

  @Test
  public void runTest() {
    generateXml(scenarioTags);
  }

  private void generateXml(String[] tags) {
    int totalDevice = availableDevices.length;
    int totalTest = tags.length;

    int totalThread = totalTest ; //Or device

    XmlSuite xmlSuite = factory.createXmlSuite(totalDevice);
    for (int i = 0; i < totalThread; i++) {
      String tag = tags[i];

      if (i >= totalDevice) break;

      Device assignedDevice = availableDevices[i];

      XmlTest test = factory.createXmlTest(tag, xmlSuite, assignedDevice);

      XmlClass xmlClass = new XmlClass();
      xmlClass.setName("MainRunner");

      test.setXmlClasses(List.of(xmlClass));
    }
    factory.saveXml(xmlSuite, filePath);
  }

  private String getScenario(String parameterizedTag) {
    return parameterizedTag == null ? System.getProperty("cucumber.filter.tags") : parameterizedTag ;
  }
}
