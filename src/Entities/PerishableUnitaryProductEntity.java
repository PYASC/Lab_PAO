package Entities;

public class PerishableUnitaryProductEntity extends UnitaryProductEntity {
    private int lifespan;

    public PerishableUnitaryProductEntity(int productId, String prodcutName, float productPrice, int categoryId, int lifespan) {
        super(productId, prodcutName, productPrice, categoryId);
        this.lifespan = lifespan;
    }
    public PerishableUnitaryProductEntity(UnitaryProductEntity p, int lifespan){
        super(p.getProductId(), p.getProductName(),p.getProductPrice(),p.getCategoryId());
        this.lifespan = lifespan;
    }

    @Override
    public String toString() {
        return "PerishableUnitaryProductEntity{" +
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
