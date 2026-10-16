package org.example.test.features.onboarding

import io.appium.java_client.AppiumDriver
import org.example.test.base.BaseTestPage
import org.openqa.selenium.By

abstract class OnboardingPage(driver: AppiumDriver): BaseTestPage(driver) {
    abstract val PROMO_BUTTON: By
    abstract val STORE_BUTTON: By
    abstract val IDENTITY_NUMBER_FIELD: By
    abstract val LOGIN_BUTTON: By

    fun inputIdentityNumber(identityNumber: String) {
        typeText(IDENTITY_NUMBER_FIELD, identityNumber)
    }

    fun tapLoginButton() {
        tap(LOGIN_BUTTON)
    }

    fun isPromoButtonVisible(): Boolean {
        return try {
            val view = findView(PROMO_BUTTON)
            println("VISLOG ${view}")
            view?.isDisplayed ?: false
        } catch (e: Exception) {
            false
        }
    }

    fun isStoreButtonVisible(): Boolean {
        return findView(STORE_BUTTON) != null
    }
}