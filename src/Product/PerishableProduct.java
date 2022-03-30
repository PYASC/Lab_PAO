package Product;


import java.util.Date;

public class PerishableProduct extends Product{
    private final Date expiringDate;

    public PerishableProduct(float price, String productName, ProductCategory category, Date expiringDate) {
        super(price, productName, category);
        this.expiringDate = new Date(expiringDate.getTime());
    }

    public PerishableProduct(Product p, Date expiringDate) {
        super(p);
        this.expiringDate = expiringDate;
    }

    public boolean isExpired(){
        return this.expiringDate.before(new Date());
    }

    @Override
    public String toString() {
        return super.toString() + "Expires at:" + this.expiringDate.toString() + "\n";
    }

}
