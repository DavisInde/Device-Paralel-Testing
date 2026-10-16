package org.example.test.features.login

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class AndroidLoginPage(driver: AndroidDriver) : LoginPage(driver) {
    override val PASSWORD_FIELD: By = By.id("belum ada")
    override val LOGIN_BUTTON: By = By.id("belum ada")
    override val INCORRECT_PASSWORD_MESSAGE: By = By.id("belum ada")

    override fun hideKeyboard() {
        //ga tau
    }
}
