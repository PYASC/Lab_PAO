package Distributor;

import Product.Product;
import Product.ProductBatch;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Distributor implements IDistributor{
    private final int id;
    private final String name;
    private final Set<Product> products; // products that the distributor has to offer
    private final float discount; // when buying from this distributor the store only pays (1-discount)*price for a certain product


    public Distributor(int id, String name, float discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        products = new HashSet<Product>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public Set<Product> getProducts() {
//        return new HashSet<Product>(products);
//    }


    public float getDiscount() {
        return discount;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public boolean hasProduct(Product product) {
        return this.products.contains(product);
    }

    @Override
    public ProductBatch orderProductBatch(Product product, float quantity) {
        if(this.hasProduct(product))
            return new ProductBatch(id, product, LocalDate.now(), quantity, this);

        return null;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "name='" + name + '\'' +
                ", products=" + products +
                ", discount=" + discount +
                '}';
    }
}
