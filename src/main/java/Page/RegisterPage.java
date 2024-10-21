package Page;

import Evidence.EvidenceGenerator;
import Utils.WebUtils;
import com.itextpdf.text.DocumentException;
import datafiles.TestDataReader;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static Core.DriverFactory.getDriver;

public class RegisterPage extends BasePage {


    public RegisterPage() {
        PageFactory.initElements(getDriver(), this);
    }

    TestDataReader data = new TestDataReader();
    EvidenceGenerator evidenceGenerator = new EvidenceGenerator();


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


    // ================== METHODS =================== //

    public void acessLoginPage() throws DocumentException, IOException {
        WebUtils.highlightElement(btnSignIn);
        evidenceGenerator.takeScreenshot("Login Page acessada!");
        btnSignIn.click();
    }

    public void login() throws DocumentException, IOException {
        LOG.info("Inserting login username");
        WebUtils.highlightElement(inputEmail);
        inputEmail.sendKeys(data.getDt("login-csv", "User"));
        LOG.info("Inserting login password");
        WebUtils.highlightElement(inputSenha);
        inputSenha.sendKeys(data.getDt("login-csv", "Password").toString());
        LOG.info("Clicking in button Log in");
        evidenceGenerator.takeScreenshot("Inserido dados de Login!");
        WebUtils.highlightElement(btnLogIn);
        btnLogIn.click();
    }

    public void new_login() throws DocumentException, IOException {
        LOG.info("Inserting login username");
        WebUtils.highlightElement(inputEmail);
        System.out.println(data.getDt("User"));
        inputEmail.sendKeys(data.getDt("User"));
        LOG.info("Inserting login password");
        WebUtils.highlightElement(inputSenha);
        inputSenha.sendKeys(data.getDt("Password"));
        LOG.info("Clicking in button Log in");
        evidenceGenerator.takeScreenshot("Dados de login preenchidos!");
        WebUtils.highlightElement(btnLogIn);
        btnLogIn.click();
    }

    public void validateSucessfullyLogin() throws DocumentException, IOException {
        LOG.info("Validating title after log in.");
        WebUtils.highlightElement(titleMyAccount);
        Assert.assertEquals("MY ACCOUNT", titleMyAccount.getText());
        Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", textLoginWelcome.getText());
        evidenceGenerator.takeScreenshot("Tela de usuário logado validado com sucesso!");
    }

}
