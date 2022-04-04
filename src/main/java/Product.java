public class Product {
    // Data Properties
    private String productID;
    private String productName;
    private double productPrice;

    // Constructor
    public Product() {

    }

    public Product(String producID, String productName, double productPrice) {
        this.productID = producID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    // region STANDARD
    // Setter
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    // Getter
    public String getProductID() {
        return productID;
    }

    public String getproductName() {
        return productName;
    }

    public double getproductPrice() {
        return productPrice;
    }

    // endregion STANDARD
    // Method
    public String toString() {
        return String.format("Product ID: %s\nProduct Name: %s\nProduct Price: RM %f", productID, productName,
                productPrice);
    }
}
