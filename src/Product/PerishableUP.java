package Product;

import java.util.Date;

public class PerishableUP extends UnitaryProduct implements Perishable{
    private final int lifespan; // days till expiry after production

    public PerishableUP(int id, float price, String productName, int lifespan) {
        super(id, price, productName);
        this.lifespan = lifespan;
    }

    public PerishableUP(int id, float price, String productName, ProductCategory category, int lifespan) {
        super(id, price, productName, category);
        this.lifespan = lifespan;
    }

    public PerishableUP(Product p, int lifespan) {
        super(p);
        this.lifespan = lifespan;
    }

    @Override
    public int getLifespan() {
        return lifespan;
    }

    @Override
    public String toString() {
        return "PerishableUP{" +
                "lifespan=" + lifespan +
                "} " + super.toString();
    }
}
