package org.example.test.features.login

import io.appium.java_client.AppiumDriver
import org.example.test.base.BaseTestPage
import org.openqa.selenium.By

abstract class LoginPage(driver: AppiumDriver) : BaseTestPage(driver) {
    abstract val PASSWORD_FIELD: By
    abstract val LOGIN_BUTTON: By
    abstract val INCORRECT_PASSWORD_MESSAGE: By

    fun waitUntilLoginPageVisible() {
        waitUntilPresent(PASSWORD_FIELD)
    }

    fun inputPassword(password: String) {
        typeText(PASSWORD_FIELD, password)
    }

    fun tapLoginButton() {
        tap(LOGIN_BUTTON)
    }

    abstract fun hideKeyboard()

    fun isPasswordFieldVisible(): Boolean {
        return isVisible(PASSWORD_FIELD)
    }

    fun isIncorrectPasswordMessageVisible(): Boolean {
        return try {
            waitUntilPresent(INCORRECT_PASSWORD_MESSAGE)
            true
        } catch (e: Exception) {
            false
        }
    }
}
