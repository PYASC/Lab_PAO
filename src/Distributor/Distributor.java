package Distributor;

import Product.Product;
import Product.ProductBatch;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Distributor implements IDistributor{
    private final String name;
    private final Set<Product> products; // products that the distributor has to offer
    private final float discount; // when buying from this distributor the store only pays (1-discount)*price for a certain product


    public Distributor(String name, float discount) {
        this.name = name;
        this.discount = discount;
        products = new HashSet<Product>();
    }

    public String getName() {
        return name;
    }

//    public Set<Product> getProducts() {
//        return new HashSet<Product>(products);
//    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public boolean hasProduct(Product product) {
        for(Product p: this.products){
            if(product.equals(p))
                return true;
        }
        return false;
    }

    @Override
    public ProductBatch orderProductBatch(Product product, float quantity) {
        return new ProductBatch(product, LocalDate.now(), quantity, this);
    }
}
