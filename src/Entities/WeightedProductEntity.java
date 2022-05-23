package Entities;

import Product.MeasuringUnit;

public class WeightedProductEntity extends UnitaryProductEntity {
    private MeasuringUnit unit;

    public WeightedProductEntity(int productId, String prodcutName, float productPrice, int categoryId, MeasuringUnit unit) {
        super(productId, prodcutName, productPrice, categoryId);
        this.unit = unit;
    }
    public WeightedProductEntity(UnitaryProductEntity p, MeasuringUnit unit){
        super(p.getProductId(), p.getProductName(), p.getProductPrice(), p.getCategoryId());
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "WeightedProductEntity{" +
                "unit=" + unit +
                "} " + super.toString();
    }

    public MeasuringUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasuringUnit unit) {
        this.unit = unit;
    }
}
