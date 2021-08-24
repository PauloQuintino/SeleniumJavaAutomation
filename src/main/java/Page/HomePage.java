package Page;

import Helper.ValidationHelpers;
import datafiles.TestDataReader;
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

    @FindBy(id = "layer_cart_product_title")
    private WebElement spanProductName;

    @FindBy(id = "layer_cart_product_attributes")
    private WebElement spanProductColorSize;

    @FindBy(id = "layer_cart_product_quantity")
    private WebElement spanProductQuantity;

    @FindBy(id = "layer_cart_product_price")
    private WebElement spanProductUnitPrice;

    @FindBy(className = "ajax_block_products_total")
    private WebElement spanTotalProductsPrice;

    @FindBy(xpath = "//span[@class='ajax_cart_shipping_cost']")
    private WebElement spanTotalShippingPrice;

    @FindBy(xpath = "//span[@class='ajax_block_cart_total']")
    private WebElement spanTotalValue;

    // ================== CLASSES =================== //

    TestDataReader data = new TestDataReader();
    ValidationHelpers helpers = new ValidationHelpers();

    // ================== METHODS =================== //

    public void accessHomePage() {
        homeLogo.click();
    }

    public void addProductInCartAtHomePage() throws InterruptedException {
        scrollDown();
        WebElement product = getDriver().findElement(By.xpath("(//a[@title='" + data.getDt("product") + "']/../../div[@class='product-image-container'])[1]"));
        mouseOverOnElement(product);
        Thread.sleep(2000);
        WebElement productClick = getDriver().findElement(By.xpath("(//h5/a[@title='" + data.getDt("product") + "']/../../div[@class='button-container']/a[@title='Add to cart'])[1]"));
        productClick.click();
        Thread.sleep(4000);
        Assert.assertEquals("Product successfully added to your shopping cart", successMessageAddToCart.getText().trim());

        String[] attributes = spanProductColorSize.getText().split(",");
        helpers.setProductName(spanProductName.getText().trim());
        helpers.setColor(attributes[0].trim());
        helpers.setSize(attributes[1].trim());
        helpers.setQuantity(Integer.parseInt(spanProductQuantity.getText().trim()));
        helpers.setUnitPrice(spanProductUnitPrice.getText().replace("$", "").trim());
        helpers.setShippingTax(spanTotalShippingPrice.getText().replace("$", "").trim());
        btnProceedToCheckout.click();
    }


}
