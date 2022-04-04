package Product;

public class WeightedProduct extends Product{ // The price is considered price per unit of measurement
    private final MeasuringUnit measuringUnit;

    public WeightedProduct(float price, String productName, MeasuringUnit measuringUnit) {
        super(price, productName);
        this.measuringUnit = measuringUnit;
    }

    public WeightedProduct(float price, String productName, ProductCategory category, MeasuringUnit measuringUnit) {
        super(price, productName, category);
        this.measuringUnit = measuringUnit;
    }

    public WeightedProduct(Product p, MeasuringUnit measuringUnit) {
        super(p);
        this.measuringUnit = measuringUnit;
    }
}
