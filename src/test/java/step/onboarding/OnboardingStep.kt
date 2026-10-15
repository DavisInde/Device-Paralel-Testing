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