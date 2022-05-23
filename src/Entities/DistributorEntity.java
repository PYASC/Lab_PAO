package Entities;

public class DistributorEntity {
    private int distributorId;
    private String distributorName;
    private float discount;

    @Override
    public String toString() {
        return "DistributorEntity{" +
                "distributorId=" + distributorId +
                ", distributorName='" + distributorName + '\'' +
                ", discount=" + discount +
                '}';
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public DistributorEntity(int distributorId, String distributorName, float discount) {
        this.distributorId = distributorId;
        this.distributorName = distributorName;
        this.discount = discount;
    }
}
