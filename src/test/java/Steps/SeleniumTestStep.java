package Steps;

import static org.junit.Assert.assertTrue;

import Page.CheckoutPage;
import Page.ProductPage;
import Page.RegisterPage;
import com.itextpdf.text.DocumentException;
import io.cucumber.java.en.And;


import Page.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;


public class SeleniumTestStep {


    private HomePage homePage = new HomePage();
    private RegisterPage registerPage = new RegisterPage();
    private CheckoutPage checkoutPage = new CheckoutPage();
    private ProductPage productPage = new ProductPage();


    // >>>>>>>>>>>>>>> login-csv <<<<<<<<<<<<<<<<
    @Given("that I am logged")
    public void thatIAmLogged() throws Exception {
//        registerPage.acessLoginPage();
        registerPage.login();
        registerPage.validateSucessfullyLogin();
    }

    @Given("I am at the home page")
    public void homePage() {
        homePage.validateHomePage();
    }

    @Given("I am in login page")
    public void IAmInLoginPage() throws DocumentException, IOException {
        registerPage.acessLoginPage();
    }

    @When("input user and password correctly")
    public void inputUserAndPasswordCorrectly() throws Exception {
        registerPage.new_login();
    }

    @Then("the login is successful")
    public void theLoginIsSuccessful() throws InterruptedException, DocumentException, IOException {
        registerPage.validateSucessfullyLogin();
    }

    // >>>>>>>>>>>>>>> buy-clothes-csv <<<<<<<<<<<<<<<<

    @Given("choose the product to buy")
    public void chooseTheProductToBuy() throws InterruptedException, DocumentException, IOException {
        homePage.addProductToCart();
        productPage.validateProductPager();
        productPage.chooseProductOptionsAndAddToCart();
    }

    @And("validate the product on the checkout page")
    public void validateTheProductInCheckoutPage() {
        checkoutPage.validateChechoutPage();
        checkoutPage.validateProductInShoppingCart();
    }

    @And("validate the adress")
    public void validateTheAdress() {
        checkoutPage.validateDeliveryAddressCheckout();
        checkoutPage.acceptShippingTerms();
    }

    @When("choose the payment method")
    public void chooseThePaymentMethod() throws InterruptedException {
        checkoutPage.paymentMethod();
    }

    @Then("the purchase have to be successfully done")
    public void thePurchaseHaveToBeSuccessfullyDone() throws InterruptedException {
        checkoutPage.confirmOrder();
    }

    @And("the Order ID should appears at Order History")
    public void theOrderIdShouldAppearsAtOrderHistory() throws InterruptedException {
        checkoutPage.validateOrderHistory();
    }


}
