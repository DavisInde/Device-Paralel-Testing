package org.example.test.features.onboarding

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class AndroidOnboardingPage(driver: AndroidDriver): OnboardingPage(driver) {
    override val PROMO_BUTTON: By = By.id("belum ada")
    override val STORE_BUTTON: By = By.id("belum ada")
    override val IDENTITY_NUMBER_FIELD: By = By.id("belum ada")
    override val LOGIN_BUTTON: By = By.id("belum ada")
    override val LOGIN_CARD: By = TODO("Not yet implemented")
    override val ALFAMART_LOGO: By = TODO("Not yet implemented")
}