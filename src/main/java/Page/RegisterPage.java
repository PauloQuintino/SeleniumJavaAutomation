package Page;

import Utils.WebUtils;
import datafiles.TestDataReader;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Core.DriverFactory.getDriver;

public class RegisterPage extends BasePage {


    public RegisterPage() {
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

    // ================== CLASSES =================== //

    TestDataReader data = new TestDataReader();
    WebUtils utils = new WebUtils();

    public void acessAutomationPraticePage() {
        getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    public void acessLoginPage() {
        utils.highlightElement(btnSignIn);
        btnSignIn.click();
    }

    public void login() {
        utils.highlightElement(inputEmail);
        inputEmail.sendKeys(data.getDt("login-csv", "User"));
        utils.highlightElement(inputSenha);
        inputSenha.sendKeys(data.getDt("login-csv", "Password").toString());
        utils.highlightElement(btnLogIn);
        btnLogIn.click();
    }

    public void new_login() {
        utils.highlightElement(inputEmail);
        inputEmail.sendKeys(data.getDt("User"));
        utils.highlightElement(inputSenha);
        inputSenha.sendKeys(data.getDt("Password"));
        utils.highlightElement(btnLogIn);
        btnLogIn.click();
    }

    public void validateSucessfullyLogin() {
        utils.highlightElement(titleMyAccount);
        Assert.assertEquals("MY ACCOUNT", titleMyAccount.getText());
        Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", textLoginWelcome.getText());
    }

}
