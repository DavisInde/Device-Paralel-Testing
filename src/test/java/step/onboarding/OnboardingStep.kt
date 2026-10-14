package step.onboarding

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.example.test.base.BaseStep
import org.example.test.context.GlobalContext
import org.example.test.features.PageProvider
import org.example.test.features.onboarding.AndroidOnboardingPage
import org.example.test.features.onboarding.OnboardingPage
import org.example.test.features.onboarding.iOSOnboardingPage

class OnboardingStep: BaseStep<OnboardingPage>() {
    override val targetPage: OnboardingPage  =
        PageProvider.getPage(
            GlobalContext.getAppiumDriver(),
            ::AndroidOnboardingPage,
            ::iOSOnboardingPage
        )

    @Given("User is on onboarding page")
    fun onboardingPageIsVisible() {
        println("Aku di onboarding")
    }

    @Then("promo button should be visible")
    fun promoButtonIsVisible() {
        targetPage.isPromoButtonVisible()
    }

    @Given("store button should be visible")
    fun storeButtonIsVisible() {
        targetPage.isStoreButtonVisible()
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