package Entities;

// maps to Product and UnitaryProduct classes since they are identical;
// design flaw; too late to fix; remove abstract from class product and implement a toString method;
public class UnitaryProductEntity {
    private int productId;
    private String productName;
    private float productPrice;
    private int categoryId;

    public UnitaryProductEntity(int productId, String productName, float productPrice, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", categoryId=" + categoryId +
                '}';
    }
}
