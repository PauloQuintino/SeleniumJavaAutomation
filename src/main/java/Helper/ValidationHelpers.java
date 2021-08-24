package Helper;

public class ValidationHelpers {

    private static String productName;
    private static String size;
    private static String color;
    private static String unitPrice;
    private static String shippingTax;
    private static int quantity;
    private static String orderId;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getShippingTax() {
        return shippingTax;
    }

    public void setShippingTax(String shippingTax) {
        this.shippingTax = shippingTax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        ValidationHelpers.orderId = orderId;
    }


    // ========== METHODS =========== //

    public float getProductsTotalPrice() {
        return quantity * Float.parseFloat(unitPrice);
    }

    public float getPurchaseTotal() {
        return getProductsTotalPrice() + Float.parseFloat(shippingTax);
    }

}
