package Product;

import org.jetbrains.annotations.NotNull;

public class ProductCategory implements Comparable<ProductCategory>{
    private final int categoryId;
    private final String categoryName;

    public ProductCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId(){
        return categoryId;
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
