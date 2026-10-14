package org.example.test.features.onboarding

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class AndroidOnboardingPage(driver: AndroidDriver): OnboardingPage(driver) {
    override val PROMO_BUTTON: By = By.id("belum ada")
    override val STORE_BUTTON: By = By.id("belum ada")
}