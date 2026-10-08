package step

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.example.test.models.Device
import org.example.test.testdriver.AndroidTest
import org.springframework.beans.factory.annotation.Autowired

class LoginStep {
    @Autowired
    var testModule: AndroidTest? = null

    @Given("Login Test")
    fun login() {
        println("hehe")
//        testModule!!.runTest(Device())
    }

    @Given("user on Splash Screen")
    fun onSplashScreen() {
        println("di splash screen")
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
