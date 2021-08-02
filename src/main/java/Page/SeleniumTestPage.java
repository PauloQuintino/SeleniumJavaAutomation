package Page;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import datafiles.ExcelApiTest;
import io.cucumber.datatable.DataTable;
import static Core.DriverFactory.getDriver;

public class SeleniumTestPage {

    public SeleniumTestPage() {
        PageFactory.initElements(getDriver(), this);
    }

    // Page mapping

    @FindBy(name = "q")
    private WebElement searchBar;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@role='listbox']//li//descendant::div[@class='zRAHie']")
    private List<WebElement> resultContents;

    @FindBy(xpath = "//div[@class='g']//h3[@class='LC20lb DKV0Md']")
    private List<WebElement> searchResults;


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

    // Methods

    public SeleniumTestPage googlePage() {
        getDriver().get("https://google.com");
        return this;
    }

    public SeleniumTestPage search(String text) throws Exception {
        ExcelApiTest data = new ExcelApiTest();
        searchBar.sendKeys(data.getDt("tag1", "SearchWord"));
        validateSearchOptions();
        searchButton.click();
        return this;
    }

    public SeleniumTestPage validateTitle() {
        assertEquals(getDriver().getTitle(), "Google");
        return this;
    }

    public void validateSearchOptions() {
        System.out.println("\n**** VALIDATING THE SEARCH OPTIONS TITLE ****\n");

        for (int i = 0; i < resultContents.size(); i++) {
            System.out.println(resultContents.get(i).getText());
        }
    }

    public boolean validateSearchResults() throws Exception {
        System.out.println("\n**** VALIDATING THE SEARCH RESULTS ****\n");
        ExcelApiTest data = new ExcelApiTest();
        boolean result = false;

        for (int i = 0; i < searchResults.size(); i++) {
            System.out.println(searchResults.get(i).getText());
            if (searchResults.get(i).getText().contains(data.getDt("tag1", "SearchWord"))) {
                result = true;
            }
        }

        return result;
    }


    public void acessAutomationPraticePage() {
        getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }


    public void acessLoginPage() {
        btnSignIn.click();
    }


    public void loginWithCSV(DataTable table) throws Exception {

        ExcelApiTest data = new ExcelApiTest();
        inputEmail.sendKeys(data.getDt("login-csv", "User"));
        inputSenha.sendKeys(data.getDt("login-csv", "Password").toString());
        btnLogIn.click();


    }

    public void validateSucessfullyLogin() {
        Assert.assertEquals("MY ACCOUNT", titleMyAccount.getText());
        Assert.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", textLoginWelcome.getText());

    }


}
