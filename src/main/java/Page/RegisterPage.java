package Page;

import Evidence.EvidenceGenerator;
import Utils.WebUtils;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import datafiles.TestDataReader;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

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
    EvidenceGenerator evidenceGenerator = new EvidenceGenerator();

    public void acessAutomationPraticePage() {
        LOG.info("Accessing automation pratice website.");
        getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    public void acessLoginPage() throws DocumentException, IOException {
        LOG.info("Clicking in button Sign In");
        utils.highlightElement(btnSignIn);
        evidenceGenerator.takeScreenshot("Login Page acessada!");
        btnSignIn.click();
    }

    public void login() throws DocumentException, IOException {
        LOG.info("Inserting login username");
        utils.highlightElement(inputEmail);
        inputEmail.sendKeys(data.getDt("login-csv", "User"));
        LOG.info("Inserting login password");
        utils.highlightElement(inputSenha);
        inputSenha.sendKeys(data.getDt("login-csv", "Password").toString());
        LOG.info("Clicking in button Log in");
        evidenceGenerator.takeScreenshot("Inserido dados de Login!");
        utils.highlightElement(btnLogIn);
        btnLogIn.click();
    }

    public void new_login() throws DocumentException, IOException {
        LOG.info("Inserting login username");
        utils.highlightElement(inputEmail);
        System.out.println(data.getDt("User"));
        inputEmail.sendKeys(data.getDt("User"));
        LOG.info("Inserting login password");
        utils.highlightElement(inputSenha);
        inputSenha.sendKeys(data.getDt("Password"));
        LOG.info("Clicking in button Log in");
        evidenceGenerator.takeScreenshot("Dados de login preenchidos!");
        utils.highlightElement(btnLogIn);
        btnLogIn.click();
    }

    public void validateSucessfullyLogin() throws DocumentException, IOException {
        LOG.info("Validating title after log in.");
        utils.highlightElement(titleMyAccount);
        Assert.assertEquals("MY ACCOUNT", titleMyAccount.getText());
        Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", textLoginWelcome.getText());
        evidenceGenerator.takeScreenshot("Tela de usuário logado validado com sucesso!");
    }

}
