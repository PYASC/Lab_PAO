package Product;

public class UnitaryProduct extends Product{

    public UnitaryProduct(int id, float price, String productName) {
        super(id, price, productName);
    }

    public UnitaryProduct(int id, float price, String productName, ProductCategory category) {
        super(id, price, productName, category);
    }

    public UnitaryProduct(Product p) {
        super(p);
    }
}
