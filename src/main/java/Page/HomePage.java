package Page;

import datafiles.ExcelApiTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Core.DriverFactory.getDriver;

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    // ================== MAPPING =================== //

    @FindBy(xpath = "//a[@title='My Store']/img")
    private WebElement homeLogo;

    @FindBy(xpath = "//h2[contains(.,'Product successfully added to your shopping cart')]")
    private WebElement successMessageAddToCart;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement btnProceedToCheckout;


    // ================== CLASSES =================== //

    ExcelApiTest data = new ExcelApiTest();

    // ================== METHODS =================== //

    public void accessHomePage(){
        homeLogo.click();
    }

    public void addProductInCartAtHomePage() throws InterruptedException {
        scrollDown();
        WebElement product = getDriver().findElement(By.xpath("(//a[@title='" + data.getDt("buy-clothes-csv", "product") + "']/../../div[@class='product-image-container'])[1]"));
        mouseOverOnElement(product);
        Thread.sleep(2000);
        WebElement productClick = getDriver().findElement(By.xpath("(//h5/a[@title='" + data.getDt("buy-clothes-csv", "product") + "']/../../div[@class='button-container']/a[@title='Add to cart'])[1]"));
        productClick.click();
        Thread.sleep(4000);
        Assert.assertEquals("Product successfully added to your shopping cart", successMessageAddToCart.getText().trim());
        btnProceedToCheckout.click();
    }


}
