package Product;

public class WeightedProduct extends Product{ // The price is considered price per unit of measurement
    private final MeasuringUnit measuringUnit;

    public WeightedProduct(int id, float price, String productName, MeasuringUnit measuringUnit) {
        super(id, price, productName);
        this.measuringUnit = measuringUnit;
    }

    public WeightedProduct(int id, float price, String productName, ProductCategory category, MeasuringUnit measuringUnit) {
        super(id, price, productName, category);
        this.measuringUnit = measuringUnit;
    }

    public WeightedProduct(Product p, MeasuringUnit measuringUnit) {
        super(p);
        this.measuringUnit = measuringUnit;
    }

    @Override
    public String toString() {
        return "WeightedProduct{" +
                "measuringUnit=" + measuringUnit +
                "} " + super.toString();
    }

    public MeasuringUnit getMeasuringUnit() {
        return measuringUnit;
    }
}
