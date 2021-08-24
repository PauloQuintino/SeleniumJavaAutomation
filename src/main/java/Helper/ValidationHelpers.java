package Helper;

public class ValidationHelpers {

    private String productName;
    private String size;
    private String color;
    private float unitPrice;
    private float shippingTax;
    private int quantity;


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

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getShippingTax() {
        return shippingTax;
    }

    public void setShippingTax(float shippingTax) {
        this.shippingTax = shippingTax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ========== METHODS =========== //

    public float getTotalPrice(){
        return (quantity * unitPrice) + shippingTax;
    }

}
