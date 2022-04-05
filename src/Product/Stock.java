package Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock implements IStock{
    private final Map<Product, List<ProductBatch>> stock;

    public Stock() {
        this.stock = new HashMap<Product, List<ProductBatch>>();
    }

    @Override
    public List<Product> getProductsInStock() {
        return null;
    }

    @Override
    public void clearExpiredProducts() {

    }

    @Override
    public boolean retrieveProductQuantity(Product product, float requestedQuantity) {
        return false;
    }

    @Override
    public void supplyProduct(ProductBatch newBatch) {

    }
}
