package Entities;

import Product.MeasuringUnit;

import java.nio.file.ClosedFileSystemException;

public class PerishableWeightedProductEntity extends WeightedProductEntity{
    private int lifespan;

    public PerishableWeightedProductEntity(int productId, String prodcutName, float productPrice, int categoryId, MeasuringUnit unit, int lifespan) {
        super(productId, prodcutName, productPrice, categoryId, unit);
        this.lifespan = lifespan;
    }
    public PerishableWeightedProductEntity(WeightedProductEntity p, int lifespan) {
        super(p, p.getUnit());
        this.lifespan = lifespan;
    }

    @Override
    public String toString() {
        return "PerishableWeightedProductEntity{" +
                "lifespan=" + lifespan +
                "} " + super.toString();
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }
}
