package Product;

import java.util.ArrayList;
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
        return new ArrayList<Product>(this.stock.keySet());
    }

    @Override
    public void  removeExpiredBatches() {
        for(Product p: this.stock.keySet()){
            if(stock.get(p).size() > 0){
                stock.get(p).removeIf(batch -> batch.isExpired());
            }

            if(stock.get(p).size() == 0){
                stock.remove(p);
            }
        }
    }

    @Override
    public boolean retrieveProductQuantity(Product product, float requestedQuantity) {
        List<ProductBatch> lpb = stock.get(product);
        int i=0;
        float q=0;
        while(q<requestedQuantity && i<lpb.size()){
            q += lpb.get(i).getQuantity();
            ++i;
        }
        if(q < requestedQuantity){
            System.out.println("Order can not be satisfied. Not enough products in stock");
            // throw InsufficientProductsError;
            return false;
        }
        for(int j=0;j<i;++j){
            requestedQuantity -= lpb.get(0).getQuantity();
            lpb.remove(0);
        }
        if(lpb.isEmpty()){
            stock.remove(product);
            return true;
        }
        if(lpb.get(0).getQuantity() == requestedQuantity)
            lpb.remove(0);
        else
            lpb.get(0).setQuantity(lpb.get(0).getQuantity() - requestedQuantity);

        return true;
    }

    @Override
    public void supplyProduct(ProductBatch newBatch) {
        if(stock.containsKey(newBatch.getProduct())){
            stock.get(newBatch.getProduct()).add(newBatch);
        }
        else{
            stock.put(newBatch.getProduct(), new ArrayList<>());
            stock.get(newBatch.getProduct()).add(newBatch);
        }
    }
}
