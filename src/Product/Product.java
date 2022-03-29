package Product;

import java.util.ArrayList;

public class Product {
    private static int productCount;
    private final int productId;
    private float price;
    private String productName;
    private ProductCategory category;

    static {
        productCount = 0;
    }

    public Product(float price, String productName, ProductCategory category) {
        this.productId = ++ Product.productCount;
        this.price = price;
        this.productName = productName;
        this.category = category;
    }

    public Product(Product p){
        this.productId = p.productId;
        this.price = p.price;
        this.productName = p.productName;
        this.category = p.category;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price >0)
            this.price = price;
        else
            System.out.println("Can't set the price to a negative value");
    }

    public String getProductName() {
        return productName;
    }


}
