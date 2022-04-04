package Distributor;

import Product.Product;
import Product.ProductBatch;

public interface IDistributor {

    boolean hasProduct(Product product);

    ProductBatch orderProductBatch(Product product, float quantity); // return the cost of the order
}