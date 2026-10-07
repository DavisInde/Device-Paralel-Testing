package step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.test.models.Device;
import org.example.test.testdriver.AndroidTest;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginStep {

  @Autowired
  public AndroidTest testModule;

  @Given("Login Test")
  public void login() {
    System.out.println("hehe");
    testModule.runTest(new Device());
  }

  @And("user tap asal asalan")
  public void tapAsalAsalan() {
    System.out.println("AHAYYY");
  }

  @Then("user should see")
  public void iSee() {
    System.out.println("I see");
  }
}
