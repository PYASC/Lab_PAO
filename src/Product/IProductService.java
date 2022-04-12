package Product;

import java.util.List;

public interface IProductService {
    void addProduct(Product product);
    void removeProduct(Product product);
    void updateProductPrice(Product product, float price);
    List<Product> getProducts();
    List<Product> getProductsSortedByPrice();
    List<Product> getProductsSortedByName();
    List<Product> getProductsSortedByCategory();
}
