package Distributor;

import Product.Product;
import Product.ProductBatch;
import java.util.List;

public interface IDistributorService {
    ProductBatch orderProductBatch(Product product, float quantity);
    void addDistributor(Distributor distributor);
    List<Distributor> getDistributors();
}
