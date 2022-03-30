package Distributor;

import Product.Product;

import java.util.HashSet;
import java.util.Set;

public class Distributor {
    private String name;
    private Set<Product> products; // products that the distributor has to offer

    public Distributor(String name) {
        this.name = name;
        products = new HashSet<Product>();
    }

    public String getName() {
        return name;
    }

    public Set<Product> getProducts() {
        return new HashSet<Product>(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
