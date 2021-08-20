package Page;

import datafiles.ExcelApiTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Core.DriverFactory.getDriver;

public class SeleniumTestPage extends BasePage {

    public SeleniumTestPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // ================== MAPPING =================== //

    @FindBy(className = "login")
    private WebElement btnSignIn;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(name = "passwd")
    private WebElement inputSenha;

    @FindBy(id = "SubmitLogin")
    private WebElement btnLogIn;

    @FindBy(className = "page-heading")
    private WebElement titleMyAccount;

    @FindBy(className = "info-account")
    private WebElement textLoginWelcome;

    @FindBy(xpath = "//a[@title='My Store']/img")
    private WebElement homeLogo;


    // ================== CLASSES =================== //

    ExcelApiTest data = new ExcelApiTest();

    // ================== METHODS =================== //

    public void acessAutomationPraticePage() {
        getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    public void acessLoginPage() {
        btnSignIn.click();
    }

    public void login() {
        inputEmail.sendKeys(data.getDt("login-csv", "User"));
        inputSenha.sendKeys(data.getDt("login-csv", "Password").toString());
        btnLogIn.click();
    }

    public void validateSucessfullyLogin() {
        Assert.assertEquals("MY ACCOUNT", titleMyAccount.getText());
        Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", textLoginWelcome.getText());
    }


    public void addProductInCartAtHomePage() throws InterruptedException {
        homeLogo.click();
        scrollDown();
        Thread.sleep(2000);
        WebElement product = getDriver().findElement(By.xpath("(//a[@title='" + data.getDt("buy-clothes-csv", "product") + "']/../../div[@class='product-image-container'])[1]"));
        mouseOverOnElement(product);
        Thread.sleep(2000);
        WebElement productClick = getDriver().findElement(By.xpath("(//h5/a[@title='" + data.getDt("buy-clothes-csv", "product") + "']/../../div[@class='button-container']/a[@title='Add to cart'])[1]"));
        productClick.click();
        Thread.sleep(4000);
    }


}
