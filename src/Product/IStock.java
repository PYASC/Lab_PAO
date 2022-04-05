package Product;

import java.util.List;

public interface IStock {
    List<Product> getProductsInStock();
    void supplyProduct(ProductBatch newBatch); // add new batch to the stock
    boolean retrieveProductQuantity(Product product, float requestedQuantity); /// return true if retrieval was possible; false otherwise
    // Check quantity is integer for UP and for perishable products check if the batches have not expired
    // Also when retrieving products always get the oldest ones - either make sure that the new batch is produced after all the previous ones or sort them again
    void clearExpiredProducts(); /// remove all batches that have expired; should be called at the start of each day to make sure there are no expired products in the stock
}
