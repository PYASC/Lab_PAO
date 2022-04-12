package Product;

public class PerishableWP extends WeightedProduct implements Perishable{
    private final int lifespan; // days till expiry after production
    public PerishableWP(float price, String productName, MeasuringUnit measuringUnit, int lifespan) {
        super(price, productName, measuringUnit);
        this.lifespan = lifespan;
    }

    public PerishableWP(float price, String productName, ProductCategory category, MeasuringUnit measuringUnit, int lifespan) {
        super(price, productName, category, measuringUnit);
        this.lifespan = lifespan;
    }

    public PerishableWP(Product p, MeasuringUnit measuringUnit, int lifespan) {
        super(p, measuringUnit);
        this.lifespan = lifespan;
    }

    @Override
    public int getLifespan() {
        return lifespan;
    }

    @Override
    public String toString() {
        return "PerishableWP{" +
                "lifespan=" + lifespan +
                "} " + super.toString();
    }
}
