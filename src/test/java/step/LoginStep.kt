package step

import hooks.DeviceContext
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then

class LoginStep {
    @Given("Login Test")
    fun login() {
        println("Driver ready: " + DeviceContext.driver.get()?.sessionId)
    }

    @Given("user on Splash Screen")
    fun onSplash() {
        val d = DeviceContext.driver.get()
        println("Splash on ${DeviceContext.device.get().name}, page source length: ${d.pageSource.length}")
    }

    @And("user tap asal asalan")
    fun tapAsalAsalan() {
        println("AHAYYY")
    }

    @Then("user should see")
    fun iSee() {
        println("I see")
    }
}
