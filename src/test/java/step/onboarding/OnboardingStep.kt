package step.onboarding

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.example.test.base.BaseStep
import org.example.test.context.GlobalContext
import org.example.test.features.PageProvider
import org.example.test.features.onboarding.AndroidOnboardingPage
import org.example.test.features.onboarding.OnboardingPage
import org.example.test.features.onboarding.IOSOnboardingPage
import org.junit.jupiter.api.Assertions
import java.lang.Thread.sleep

class OnboardingStep: BaseStep<OnboardingPage>() {
    override val targetPage: OnboardingPage  =
        PageProvider.getPage(
            GlobalContext.getAppiumDriver(),
            ::AndroidOnboardingPage,
            ::IOSOnboardingPage
        )

    @Given("User is on onboarding page")
    fun onboardingPagesIsVisible() {
        println("Aku di onboarding")
    }

    @Then("promo button should be visible")
    fun promoButtonIsVisible() {
        val isVisible = targetPage.isPromoButtonVisible()
        Assertions.assertTrue(isVisible)
    }

    @Given("store button should be visible")
    fun storeButtonIsVisible() {
        val isVisible = targetPage.isStoreButtonVisible()
        Assertions.assertTrue(isVisible)
    }

    // Set it before running: export TEST_IDENTITY_NUMBER=<number>
    @When("user enters identity number")
    fun enterIdentityNumber() {
        val identityNumber = System.getenv("TEST_IDENTITY_NUMBER")
            ?: throw IllegalStateException("TEST_IDENTITY_NUMBER is not set")
        targetPage.inputIdentityNumber(identityNumber)
    }

    @When("user taps login button on onboarding page")
    fun tapLoginButton() {
        targetPage.tapLoginButton()
    }

    @When("user tap alfamart logo 5 times")
    fun tapAlfamartLogo() {
        repeat(5) {
            targetPage.tapAlfamartLogo()
            sleep(1000)
        }
    }

    @When("user searches for a store by name")
    fun searchStoreByName() {

    }

    @When("user searches for a store by address")
    fun searchStoreByAddress() {

    }

    @Then("matching store should be displayed")
    fun matchingStoreShouldDisplay() {

    }
}