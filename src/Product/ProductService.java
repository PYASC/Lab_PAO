package Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService implements IProductService {
    private final List<Product> products;

    public ProductService(){
        products = new ArrayList<Product>();
    }
    @Override
    public void addProduct(Product p) {
        products.add(p);
    }
    @Override
    public void removeProduct(Product p) {
        products.remove(p);
    }
    @Override
    public void updateProductPrice(Product product, float price) {

    }
    @Override
    public List<Product> getProducts() {
        return new ArrayList<Product>(products);
    }

    @Override
    public List<Product> getProductsSortedByPrice() {
        products.sort(new PriceComparator());
        return this.products;
    }
    @Override
    public List<Product> getProductsSortedByName() {
        products.sort(new NameComparator());
        return this.products;
    }

    @Override
    public List<Product> getProductsSortedByCategory() {
        products.sort(new CategoryComparator());
        return this.products;
    }
}
