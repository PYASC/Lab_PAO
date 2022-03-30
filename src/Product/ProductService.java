package Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService {
    private static List<Product> products;
    private static List<ProductCategory> categories;

    static {
        products = new ArrayList<Product>();
        categories = new ArrayList<ProductCategory>();
    }

    public static void addProduct(Product p) {
        products.add(p);
    }

    public static void removeProduct(Product p) {
        products.remove(p);
    }
    public static void removeProduct(int index) {
        try {
            products.remove(index);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bounds.");
        }
    }

    public static void updateProductPrice(int index, float newPrice) {
        try {
            products.get(index).setPrice(newPrice);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }
    public static List<Product> getProducts() {
        return new ArrayList<Product>(products);
    }

    public static void sortProductsByPrice() {
        products.sort(new PriceComparator());
    }

    public static void sortProductsByName() {
        products.sort(new NameComparator());
    }

    public static void sortProductsByCategory() {
        products.sort(new CategoryComparator());
    }

    public static void addCategory(ProductCategory cat) {
        categories.add(cat);
    }

    public static void removeCategory(ProductCategory cat) {
        categories.remove(cat);
    }

    public static void removeCategory(int index) {
        try {
            categories.remove(index);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }

    public static List<ProductCategory> getCategories() {
        return new ArrayList<ProductCategory>(categories);
    }

    public static void sortCategoriesByName() {
        categories.sort(new Comparator<ProductCategory>() {
            @Override
            public int compare(ProductCategory c1, ProductCategory c2) {
                return c1.compareTo(c2);
            }
        });
    }



}
