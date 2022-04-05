import Client.Client;
import Distributor.Distributor;
import Product.Product;
import Product.ProductBatch;
import Product.ProductCategory;
import Product.ProductService;

import java.util.*;


public class Store {

    private final List<Product> products; /// all the products that the store could have for sale
    private final List<Distributor> distributors; /// list of all the distributors the store can buy batches from
    private final IStockService stockService; /// turn into an actual class and
    private final List<Client> clients; /// list of all the registered clients ; later an Order class to save the sales

    private static Store store = null;

    private Store() {
        this.products = new ArrayList<Product>();
        this.distributors = new ArrayList<Distributor>();
        this.stockService = new StockService();
        this.clients = new ArrayList<Client>();
    }

    public static Store getInstance(){
        if(Store.store == null)
            Store.store = new Store();
        return store;
    }
    public static void main(String[] args) {

        Store store = Store.getInstance();
        Store.init();
        Store.menu();

    }

    private static void init(){
        if(Store.store == null)
            return; // mby throw a custom instance not initialized error;

        ProductService.addCategory(new ProductCategory("DairyProducts"));
        ProductService.addCategory(new ProductCategory("Meat"));

//        ProductService.addProduct(new PerishableProduct(15.8f, "Chicken Wings", ProductService.getCategories().get(1), 15));
//        ProductService.addProduct(new PerishableProduct(20.25f, "Pork Ribs", ProductService.getCategories().get(1), 17));
//        ProductService.addProduct(new PerishableProduct(4.5f, "Milk", ProductService.getCategories().get(0), 3));

    }

    private static void menu() {
        if(Store.store == null)
            return;
        Scanner in = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();

        int opt, d;
        int index;
        float p;
        String s;

        while(true){

            System.out.println("1. Show products");
            System.out.println("2. Add Product");
            System.out.println("3. Add Perishable Product");
            System.out.println("4. Remove Product");
            System.out.println("5. Update Product Price");
            System.out.println("6. Show categories");
            System.out.println("7. Add category");
            System.out.println("8. Sort Products by Name");
            System.out.println("9. Sort Products by Price");
            System.out.println("10. Sort Products by Category Name");
            System.out.println("Option: ");
            opt =  Integer.parseInt(in.nextLine());

            switch (opt) {
                case 1:
                    for(int i=1; i<= ProductService.getProducts().size(); ++i) {
                        System.out.println((i) + "." + ProductService.getProducts().get(i-1));
                    }
                    break;
                case 2:
                    System.out.println("Product name:");
                    s = in.nextLine().trim();
                    System.out.println("Price:");
                    p = Float.parseFloat(in.nextLine());
                    System.out.println("Choose Category of new product");
                    for(int i=0; i<ProductService.getCategories().size(); ++i) {
                        System.out.println("  " + (i+1) + "." + ProductService.getCategories().get(i).toString());
                    }
                    index = Integer.parseInt(in.nextLine());

                    ///ProductService.addProduct(new Product(p, s, ProductService.getCategories().get(index-1)));
                    break;
                case 3:
                    System.out.println("Product name:");
                    s = in.nextLine().trim();
                    System.out.println("Price:");
                    p = Float.parseFloat(in.nextLine());
                    System.out.println("Choose Category of new product");
                    for(int i=0; i<ProductService.getCategories().size(); ++i) {
                        System.out.println("  " + (i+1) + "." + ProductService.getCategories().get(i).toString());
                    }
                    index = Integer.parseInt(in.nextLine());
                    System.out.println("Days till the product expires:");
                    d = Integer.parseInt(in.nextLine());
                    calendar.add(Calendar.DATE, d);
                    /// ProductService.addProduct(new PerishableProduct(p, s, ProductService.getCategories().get(index-1), d));
                    calendar.add(Calendar.DATE, -d);
                    break;
                case 4:
                    System.out.println("Index of product to be removed:");
                    index = Integer.parseInt(in.nextLine());
                    ProductService.removeProduct(index-1);
                    break;
                case 5:
                    System.out.println("Index of product:");
                    index = Integer.parseInt(in.nextLine());
                    System.out.println("New price of the product:");
                    p = Float.parseFloat(in.nextLine());
                    ProductService.updateProductPrice(index-1, p);
                    break;
                case 6:
                    for(ProductCategory category: ProductService.getCategories()){
                        System.out.println(category);
                    }
                    break;
                case 7:
                    System.out.println("Category name:");
                    s = in.nextLine();
                    ProductService.addCategory(new ProductCategory(s));
                    break;
                case 8:
                    ProductService.sortProductsByName();
                    break;
                case 9:
                    ProductService.sortProductsByPrice();
                    break;
                case 10:
                    ProductService.sortProductsByCategory();
                    break;
                default:
                    System.out.println("Unknown option");
                    return;
            }

        }


    }


}
