package step.onboarding

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

class OnboardingStep {
    @Given("User is on onboarding page")
    fun onboardingPageIsVisible() {
        println("Aku di onboarding")
    }

    @Then("promo button should be visible")
    fun promoButtonIsVisible() {

    }

    @Given("store button should be visible")
    fun storeButtonIsVisible() {

    }

    @When("user searches for a store by name")
    fun searchStoreByName() {

    }

    @When("When user searches for a store by address")
    fun searchStoreByAddress() {

    }

    @Then("matching store should be displayed")
    fun matchingStoreShouldDisplay() {

    }
}