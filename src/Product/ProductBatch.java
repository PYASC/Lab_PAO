package Product;

import Distributor.Distributor;

import java.time.LocalDate;

public class ProductBatch {
    private final Product product;
    private final LocalDate productionDate;
    private float quantity;
    private final Distributor distributor;

    public ProductBatch(Product product, LocalDate productionDate, float quantity, Distributor distributor) {
        this.product = product;
        this.productionDate = productionDate;
        this.setQuantity(quantity);
        this.distributor = distributor;
    }

    public Product getProduct() {
        return product;
    }
    public Distributor getDistributor() {
        return distributor;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        if(quantity < 0){
            System.out.println("Negative quantity"); // define a throwable error later
            return;
        }

        if(this.product instanceof WeightedProduct){
            this.quantity = quantity;
            return;
        }

        if(quantity - (int)quantity > 0){
            System.out.println("Can't assign non integer to quantity for an UnitaryProduct"); // define a throwable error later for this case as well
            return;
        }
        this.quantity = quantity;
    }

    public boolean isExpired(){
        if(this.product.getClass().isAssignableFrom(Perishable.class)){
            Perishable p = (Perishable) product;
            LocalDate expiryDate = this.productionDate.plusDays(p.getLifespan());
            LocalDate now = LocalDate.now();

            return now.isAfter(expiryDate);
        }

        return false;
    }

}
