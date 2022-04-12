package Distributor;

import Product.Product;
import Product.ProductBatch;

import java.util.ArrayList;
import java.util.List;

public class DistributorService implements IDistributorService{
    private final List<Distributor> distributors;

    public DistributorService() {
        this.distributors = new ArrayList<>();
    }

    public DistributorService(List<Distributor> l){
        this.distributors = new ArrayList<>(l);
    }

    @Override
    public ProductBatch orderProductBatch(Product product, float quantity) {
        if(!ProductBatch.isValidQuantity(quantity, product))
            return null;

        Distributor d = null;
        float discount = 0;
        for(Distributor distributor: distributors){
            if(distributor.hasProduct(product) && distributor.getDiscount()>discount){
                d = distributor;
                discount = d.getDiscount();
            }
        }
        if(d==null)
            return null;

        return d.orderProductBatch(product, quantity);
    }

    @Override
    public void addDistributor(Distributor distributor) {
        this.distributors.add(distributor);
    }

    @Override
    public List<Distributor> getDistributors() {
        return new ArrayList<Distributor>(this.distributors);
    }

}
