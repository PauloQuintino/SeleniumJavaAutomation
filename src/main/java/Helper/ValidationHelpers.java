package Helper;

public class ValidationHelpers {

    private static String productName;
    private static String size;
    private static String color;
    private static String unitPrice;
    private static String shippingTax;
    private static int quantity;
    private static String orderId;


    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        ValidationHelpers.productName = productName;
    }

    public static String getSize() {
        return size;
    }

    public static void setSize(String size) {
        ValidationHelpers.size = size;
    }

    public static String getColor() {
        return color;
    }

    public static void setColor(String color) {
        ValidationHelpers.color = color;
    }

    public static String getUnitPrice() {
        return unitPrice;
    }

    public static void setUnitPrice(String unitPrice) {
        ValidationHelpers.unitPrice = unitPrice;
    }

    public static String getShippingTax() {
        return shippingTax;
    }

    public static void setShippingTax(String shippingTax) {
        ValidationHelpers.shippingTax = shippingTax;
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        ValidationHelpers.quantity = quantity;
    }

    public static String getOrderId() {
        return orderId;
    }

    public static void setOrderId(String orderId) {
        ValidationHelpers.orderId = orderId;
    }
}
