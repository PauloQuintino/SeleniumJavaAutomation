package Page;

import Evidence.EvidenceGenerator;
import Helper.ValidationHelpers;
import datafiles.TestDataReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Core.DriverFactory.getDriver;

public class ProductPage extends BasePage {

    public ProductPage() {
        PageFactory.initElements(getDriver(), this);
    }

    private static TestDataReader dataReader = new TestDataReader();
   private static EvidenceGenerator evidenceGenerator = new EvidenceGenerator();

    // ================== MAPPING =================== //

    private static String xx = "teste";

    @FindBy(className = "page-title")
    private WebElement productTitle;

    @FindBy(xpath = "(//span[@class='price'])[1]")
    private WebElement productUnitPrice;

    @FindBy(xpath = "(//div[@class='swatch-attribute-options clearfix'])[1]")
    private WebElement sizeOptionsContainer;

    @FindBy(xpath = "(//div[@class='swatch-attribute-options clearfix'])[2]")
    private WebElement colorOptionsContainer;

    @FindBy(name = "qty")
    private WebElement quantityInput;

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(className = "counter-number")
    private WebElement cartCounterNumber;

    @FindBy(xpath = "//div[@class='messages' and @role='alert']")
    private WebElement successAlert;


    // ================== METHODS =================== //

    public void validateProductPager() {
        LOG.info("Validando elementos da página de produto...");
        Assert.assertTrue(productTitle.isDisplayed());
        Assert.assertTrue(productUnitPrice.isDisplayed());
        Assert.assertTrue(sizeOptionsContainer.isDisplayed());
        Assert.assertTrue(colorOptionsContainer.isDisplayed());
        LOG.info("Elementos da página de produto validados!");
        evidenceGenerator.takeScreenshot("Página do produto validada!");
    }


    public void chooseProductOptionsAndAddToCart() {
        try {
            ValidationHelpers.setUnitPrice(productUnitPrice.getText().replace("$", ""));
            WebElement sizeButton = getDriver().findElement(By.xpath("//div[@option-label='" + dataReader.getDt("size") + "']"));
            WebElement colorButton = getDriver().findElement(By.xpath("//div[@option-label='" + dataReader.getDt("color") + "']"));
            sizeButton.click();
            colorButton.click();

            quantityInput.clear();
            quantityInput.sendKeys(dataReader.getDt("quantity"));
            addToCartButton.click();
            Thread.sleep(2000);
            Assert.assertTrue("Alerta com mensagem de sucesso não foi renderizado!",successAlert.isDisplayed());
            Assert.assertEquals(dataReader.getDt("quantity"), cartCounterNumber.getText());

            evidenceGenerator.takeScreenshot("Produto selecionado e adicionado ao carrinho!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
