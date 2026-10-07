package org.example.test.testdriver;

import org.example.test.base.Config;
import org.example.test.base.ConfigConsumer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.example.test.models.Device;
import org.example.test.utils.TestStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

public class IOSTest extends ConfigConsumer {
    IOSDriver driver = null;

    public IOSTest(Config config) {super(config);}

    public boolean runTest(Device device) {
        boolean testStatus = false;

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("appium:bundleId", config.appPackageId);
        caps.setCapability("appium:udid", device.udid);
        caps.setCapability("appium:wdaLocalPort", device.wdaLocalPort);

        try {
            driver = new IOSDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    caps
            );

            testStatus = true ;
        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
        } finally {
            driver.executeScript(
                    "devicefarm: setSessionStatus",
                    Map.of("status", TestStatus.getTestStatus(testStatus))
            ) ;
        }

        return testStatus;
    }
}
