package Product;

public class ProductBatch {
    private final Product p;
    private int nrOfUnits;

    public ProductBatch(Product p, int nrOfUnits) {
        this.p = p;
        this.nrOfUnits = nrOfUnits;
    }

    public int getNrOfUnits() {
        return nrOfUnits;
    }

    public void setNrOfUnits(int nrOfUnits) {
        this.nrOfUnits = nrOfUnits;
    }

    public Product getProduct() {
        return p;
    }
}
