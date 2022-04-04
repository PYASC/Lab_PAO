package Product;

public class UnitaryProduct extends Product{

    public UnitaryProduct(float price, String productName) {
        super(price, productName);
    }

    public UnitaryProduct(float price, String productName, ProductCategory category) {
        super(price, productName, category);
    }

    public UnitaryProduct(Product p) {
        super(p);
    }
}
