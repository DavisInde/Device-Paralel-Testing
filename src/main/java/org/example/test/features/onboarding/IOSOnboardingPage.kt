package org.example.test.features.onboarding

import io.appium.java_client.AppiumBy
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.By

class IOSOnboardingPage(driver: IOSDriver): OnboardingPage(driver) {
    override val PROMO_BUTTON: By = By.id("onboarding.header.promo")
    override val STORE_BUTTON: By = By.id("onboarding.header.store")
    override val LOGIN_BUTTON: By = By.id("onboarding.loginCard.loginButton")
    override val LOGIN_CARD: By = By.id("onboarding.loginCard.container")
    override val ALFAMART_LOGO: By = By.id("onboarding.header.logo")
    override val IDENTITY_NUMBER_FIELD: By = By.id("identityNumber")
}
