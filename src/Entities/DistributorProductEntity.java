package Entities;

public class DistributorProductEntity {
    private int distributorId, productId;

    @Override
    public String toString() {
        return "DistributorProductEntity{" +
                "distributorId=" + distributorId +
                ", productId=" + productId +
                '}';
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public DistributorProductEntity(int distributorId, int productId) {
        this.distributorId = distributorId;
        this.productId = productId;
    }
}
