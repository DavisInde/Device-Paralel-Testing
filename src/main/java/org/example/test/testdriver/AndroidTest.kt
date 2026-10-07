package org.example.test.testdriver;

import org.example.test.base.Config;
import org.example.test.base.ConfigConsumer;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.test.models.Device;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URI;

@Configuration
public class AndroidTest extends ConfigConsumer {
    public static Device device;

    public static AndroidDriver driver = null;

    public AndroidTest() {
        super(new Config());
    }

    public void runTest(Device device) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAppPackage(config.appPackageId);

        options.setCapability("platformName", "Android");
        options.setCapability("appium:udid", device.udid);
        options.setCapability("appium:systemPort", device.systemPort);
        options.setCapability("appium:adbPort", device.adbPort);
        options.setCapability("appPackage", config.appPackageId);
        options.setCapability("appActivity", config.appActivity);
        options.setCapability("mobile: startActivity", config.appActivity);

        try {
            driver = new AndroidDriver(
                    URI.create(this.config.baseUrl + "wd/hub").toURL(),
                    options
            );

        } catch (MalformedURLException e1) {
            System.out.println("Yahh " + e1);
            System.out.println("Yahh " + e1.getMessage());
        }

    }
}
