package org.example.test.features.onboarding

import io.appium.java_client.AppiumDriver
import org.example.test.base.BaseTestPage
import org.openqa.selenium.By

abstract class OnboardingPage(driver: AppiumDriver): BaseTestPage(driver) {
    abstract val PROMO_BUTTON: By
    abstract val STORE_BUTTON: By

    fun isPromoButtonVisible(): Boolean {
        return findView(PROMO_BUTTON) != null
    }

    fun isStoreButtonVisible(): Boolean {
        return findView(STORE_BUTTON) != null
    }
}