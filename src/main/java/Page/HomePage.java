package Page;

import Evidence.EvidenceGenerator;
import Helper.ValidationHelpers;
import Utils.WebUtils;
import com.itextpdf.text.DocumentException;
import datafiles.TestDataReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static Core.DriverFactory.getDriver;

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    private static TestDataReader dataReader = new TestDataReader();
    private EvidenceGenerator evidenceGenerator = new EvidenceGenerator();


    // ================== MAPPING =================== //

    @FindBy(className = "logo")
    private WebElement homeLogo;

    @FindBy(xpath = "(//a[contains(text(),'Sign In')])[1]")
    private WebElement signInButton;
    @FindBy(xpath = "(//a[contains(text(),'Create an Account')])[1]")
    private WebElement createAccountButton;


    // ================== METHODS =================== //

    public void validateHomePage() {
        LOG.info("==== Validando elementos da página inicial...");
        Assert.assertEquals("Home Page", getDriver().getTitle());
        Assert.assertTrue(homeLogo.isDisplayed());
        Assert.assertTrue(signInButton.isDisplayed());
        Assert.assertTrue(createAccountButton.isDisplayed());
        evidenceGenerator.takeScreenshot("Página incial");
    }

    public void accessHomePage() {
        homeLogo.click();
    }

    public void addProductToCart(){
        WebElement product = getDriver().findElement(By.xpath("//a[@title='" + dataReader.getDt("product") + "']"));
        scrollToElement(product);
        WebUtils.highlightElement(product);
        evidenceGenerator.takeScreenshot("Produto encontrado!");
        product.click();
    }


}
