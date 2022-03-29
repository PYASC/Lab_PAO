package Product;

import org.jetbrains.annotations.NotNull;

public class ProductCategory implements Comparable<ProductCategory>{
    private String categoryName;

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString(){
        return this.getCategoryName();
    }

    @Override
    public int compareTo(@NotNull ProductCategory c) {
        return this.categoryName.compareTo(c.getCategoryName());
    }
}
