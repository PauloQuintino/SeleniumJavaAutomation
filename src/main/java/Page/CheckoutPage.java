package Page;

import Helper.ValidationHelpers;
import datafiles.TestDataReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static Core.DriverFactory.getDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage() {
        PageFactory.initElements(getDriver(), this);
    }

    ValidationHelpers helpers = new ValidationHelpers();
    TestDataReader data = new TestDataReader();


    // ================== MAPPING =================== //

    @FindBy(id = "cart_title")
    private WebElement titleCheckoutPage;

    @FindBy(xpath = "//td[@class='cart_description']/p[@class='product-name']")
    private WebElement lblProductName;

    @FindBy(xpath = "//td[@id='total_product']")
    private WebElement tdTotalProducts;

    @FindBy(xpath = "//td[@id='total_shipping']")
    private WebElement tdTotalShipping;

    @FindBy(xpath = "//span[@id='total_price']")
    private WebElement tdTotalPrice;

    @FindBy(className = "cart_quantity_input")
    private WebElement lblQuantity;

    @FindBy(xpath = "(//li[@class='address_address1 address_address2'])[1]")
    private WebElement txtDeliveryAddress;

    @FindBy(xpath = "(//a[@title='Proceed to checkout'])[2]")
    private WebElement btnProceedToCheckoutSummary;

    @FindBy(name = "processAddress")
    private WebElement btnProceedToCheckoutAddress;

    @FindBy(id = "cgv")
    private WebElement checkboxTerms;

    @FindBy(name = "processCarrier")
    private WebElement btnProceedToCheckoutShipping;

    @FindBy(className = "page-heading")
    private WebElement lblConfirmOrderPage;

    @FindBy(id = "amount")
    private WebElement spanConfirmOrderTotalPrice;

    @FindBy(xpath = "//span[contains(.,'I confirm my order')]/../../button")
    private WebElement btnConfirmOrder;

    @FindBy(className = "cheque-indent")
    private WebElement txtOrderConfirmation;

    @FindBy(xpath = "//div[@class='box']")
    private WebElement txtConfirmationOrderId;

    @FindBy(xpath = "//a[@title='Back to orders']")
    private WebElement linkBackToOrders;



    // ================== METHODS =================== //

    public void validateChechoutPage() {
        Assert.assertTrue(titleCheckoutPage.getText().contains("SHOPPING-CART SUMMARY"));
    }

    public void validateProductInShoppingCart() {
        scrollDown();
        WebElement element = getDriver().findElement(By.xpath("(//a[contains(.,'Color')])[2]"));
        String[] lblAttributes = element.getText().split(",");
        String colorCaptured = lblAttributes[0].replaceAll("Color : ", "").trim();
        String sizeCaptured = lblAttributes[1].replaceAll("Size : ", "").trim();

        Assert.assertEquals(helpers.getProductName(), lblProductName.getText().trim());
        Assert.assertEquals(helpers.getColor(), colorCaptured);
        Assert.assertEquals(helpers.getSize(), sizeCaptured);
//        Assert.assertEquals(String.valueOf(helpers.getQuantity()), lblQuantity.getAttribute("value"));
//        Assert.assertEquals(String.valueOf(helpers.getProductsTotalPrice()), tdTotalProducts.getText().replace("$", "").trim());
//        Assert.assertEquals(helpers.getShippingTax(), tdTotalShipping.getText().replace("$", "").trim());
//        Assert.assertEquals(String.valueOf(helpers.getPurchaseTotal()), tdTotalPrice.getText().replace("$", "").trim());
        btnProceedToCheckoutSummary.click();
    }

    public void validateDeliveryAddressCheckout() {
        Assert.assertEquals(data.getDt("buy-clothes-csv", "address"), txtDeliveryAddress.getText().trim());
        btnProceedToCheckoutAddress.click();
    }

    public void acceptShippingTerms() {
        checkboxTerms.click();
        btnProceedToCheckoutShipping.click();
    }

    public void paymentMethod() throws InterruptedException {
        Thread.sleep(2000);
        scrollDown();

        if (data.getDt("buy-clothes-csv", "payment_method").equals("bankwire")) {
            WebElement payment = getDriver().findElement(By.xpath("//a[@class='bankwire']"));
            payment.click();
        } else {
            WebElement payment = getDriver().findElement(By.xpath("//a[@class='cheque']"));
            payment.click();
        }
    }

    public void confirmOrder() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertEquals("ORDER SUMMARY", lblConfirmOrderPage.getText().trim());
//        Assert.assertEquals(String.valueOf(helpers.getPurchaseTotal()), spanConfirmOrderTotalPrice.getText().replace("$", "").trim());
        btnConfirmOrder.click();
        Thread.sleep(1000);
        Assert.assertEquals("Your order on My Store is complete.", txtOrderConfirmation.getText().trim());
        String orderId = txtConfirmationOrderId.getText().replace("\n", " ").substring(216, 225).trim();
        helpers.setOrderId(orderId);
        System.out.println("ORDER ID: " + helpers.getOrderId());
    }

    public void validateOrderHistory() throws InterruptedException {
        linkBackToOrders.click();
        Thread.sleep(1000);

        List<WebElement> orderList = getDriver().findElements(By.xpath("//table[@id='order-list']/tbody/tr[@class='first_item ']/td"));
        List<String> orderListValues = new ArrayList<>();

        for (int i = 0; i < orderList.size(); i++) {
            orderListValues.add(orderList.get(i).getText().trim());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date date = new Date();

        Assert.assertEquals(helpers.getOrderId(), orderListValues.get(0));
        Assert.assertEquals(sdf.format(date), orderListValues.get(1));
//        Assert.assertEquals( String.valueOf(helpers.getPurchaseTotal()), orderListValues.get(2).replace("$", "").trim());
    }

}
