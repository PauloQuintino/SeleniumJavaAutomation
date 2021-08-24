package Steps;

import static org.junit.Assert.assertTrue;

import Page.RegisterPage;
import io.cucumber.java.en.And;


import Page.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class SeleniumTestStep {


    private HomePage homePage = new HomePage();
    private RegisterPage registerPage = new RegisterPage();


    // >>>>>>>>>>>>>>> login-csv <<<<<<<<<<<<<<<<
    @Given("that I am logged")
    public void thatIAmLogged() {
        registerPage.acessLoginPage();
        registerPage.login();
        registerPage.validateSucessfullyLogin();
    }

    @When("input user and password correctly")
    public void inputUserAndPasswordCorrectly() throws Exception {
        registerPage.login();
    }

    @Then("the login is successful")
    public void theLoginIsSuccessful() throws InterruptedException {
        registerPage.validateSucessfullyLogin();
    }

    // >>>>>>>>>>>>>>> buy-clothes-csv <<<<<<<<<<<<<<<<

    @Given("choose the product to buy")
    public void chooseTheProductToBuy() throws InterruptedException {
        homePage.accessHomePage();
        homePage.addProductInCartAtHomePage();
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
