package Entities;

public class ProductBatchEntity {
    private int batchId, productId, distributorId;
    private float quantity;
    private String productionDate;

    public ProductBatchEntity(int batchId, int productId, int distributorId, float quantity, String productionDate) {
        this.batchId = batchId;
        this.productId = productId;
        this.distributorId = distributorId;
        this.quantity = quantity;
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return "ProductBatchEntity{" +
                "batchId=" + batchId +
                ", productId=" + productId +
                ", distributorId=" + distributorId +
                ", productionDate='" + productionDate + '\'' +
                '}';
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
}
