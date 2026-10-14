package step

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.example.test.base.BaseStep
import org.example.test.context.GlobalContext
import org.example.test.features.PageProvider
import org.example.test.features.home.AndroidHomePage
import org.example.test.features.home.HomePage
import org.example.test.features.home.IosHomePage

class LoginStep: BaseStep<HomePage>() {
    override val targetPage: HomePage =
        PageProvider.getPage(
            GlobalContext.getAppiumDriver(),
            ::AndroidHomePage,
            ::IosHomePage
        )

    @Given("Login Test")
    fun login() {
        println("Driver ready: " + GlobalContext.getAppiumDriver().sessionId)
    }

    @Given("user on Splash Screen")
    fun onSplash() {
        val d = GlobalContext.getAppiumDriver()
        println("Splash on ${GlobalContext.getCurrentDevice().name}, page source length: ${d.pageSource.length}")
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
