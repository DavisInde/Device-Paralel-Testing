package org.example.test.features.login

import io.appium.java_client.AppiumBy
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.By

class IOSLoginPage(driver: IOSDriver): LoginPage(driver) {
    val KEYBOARD_DONE_BUTTON: By = By.id("Done")
    override val LOGIN_BUTTON: By = By.id("login.form.loginButton")
    override val PASSWORD_FIELD: By = AppiumBy.iOSNsPredicateString(
        "type == 'XCUIElementTypeSecureTextField' AND name == 'login.form.passwordField'"
    )
    override val INCORRECT_PASSWORD_MESSAGE: By = AppiumBy.iOSNsPredicateString(
        "type == 'XCUIElementTypeStaticText' AND label CONTAINS 'Incorrect password'"
    )

    override fun hideKeyboard() {
        tap(KEYBOARD_DONE_BUTTON)
    }
}
