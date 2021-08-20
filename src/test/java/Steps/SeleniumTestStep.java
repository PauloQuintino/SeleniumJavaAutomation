package Steps;

import static Core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import Page.SeleniumTestPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class SeleniumTestStep {


    private SeleniumTestPage seleniumTestPage = new SeleniumTestPage();
    ;


    // >>>>>>>>>>>>>>> login-csv <<<<<<<<<<<<<<<<
    @Given("that I am logged")
    public void thatIAmLogged() {
        seleniumTestPage.acessLoginPage();
        seleniumTestPage.login();
        seleniumTestPage.validateSucessfullyLogin();
    }

    @When("input user and password correctly")
    public void inputUserAndPasswordCorrectly() throws Exception {
        seleniumTestPage.login();
    }

    @Then("the login is successful")
    public void theLoginIsSuccessful() throws InterruptedException {
        seleniumTestPage.validateSucessfullyLogin();
    }

    // >>>>>>>>>>>>>>> buy-clothes-csv <<<<<<<<<<<<<<<<

    @Given("choose the product to buy")
    public void chooseTheProductToBuy() throws InterruptedException {
        seleniumTestPage.addProductInCartAtHomePage();
    }

    @And("validate the product on the checkout page")
    public void validateTheProductInCheckoutPage() {

    }

    @And("validate the adress")
    public void validateTheAdress() {

    }

    @When("choose the payment method")
    public void chooseThePaymentMethod() {

    }

    @Then("the purchase have to be successfully done")
    public void thePurchaseHaveToBeSuccessfullyDone() {

    }


}
