package Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

class PriceComparator implements Comparator<Product>{
    @Override
    public int compare(Product p1, Product p2) {
        return Float.compare(p1.getPrice(), p2.getPrice());
    }
}

class NameComparator implements Comparator<Product>{
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getProductName().compareTo(p2.getProductName());
    }
}

class CategoryComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCategory().compareTo(p2.getCategory());
    }
}


public class Product {
    private final int productId;
    private float price;
    private final String productName;
    private ProductCategory category;

    public Product(int productId, float price, String productName) {
        this.productId = productId;
        this.price = price;
        this.productName = productName;
        this.category = null;
    }

    public Product(int productId, float price, String productName, ProductCategory category) {
        this.productId = productId;
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

    public ProductCategory getCategory() {
        return category;
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

    public int getProductId(){
        return productId;
    }
    public void setCategory(ProductCategory category){
        this.category = category;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price= " + price +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
