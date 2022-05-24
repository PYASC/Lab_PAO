import Client.Client;
import Distributor.Distributor;
import Distributor.IDistributorService;
import Product.Product;
import Product.ProductBatch;
import Product.UnitaryProduct;
import Product.WeightedProduct;
import Product.PerishableUP;
import Product.PerishableWP;
import Product.ProductCategory;
import Product.MeasuringUnit;
import Product.IProductService;
import Product.IStock;
import Product.Stock;
import Services.DistributorService;
import Services.ProductService;

import java.util.*;


public class Store {
//    private final List<ProductCategory> productCategories;
//    private final IProductService productsService;
//    private final IDistributorService distributorService;
//    private final IStock stock;
    //private final List<Client> clients;

   // private static Store store = null;

//    private Store() {
//        this.productCategories = new ArrayList<ProductCategory>();
//        this.productsService = new ProductService();
//        this.distributorService = new DistributorService();
//        this.stock = new Stock();
//        //this.clients = new ArrayList<Client>();
//    }

//    public static Store getInstance(){
//        if(Store.store == null)
//            Store.store = new Store();
//        return store;
//    }
    public static void main(String[] args) {

        // getInstance();
        // store.init();

        try {
            // store.menu();
            System.out.println("ASD");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally { /// used for saving to csv and db later
            //Store.saveCSV();
            //Store.saveDB();
        }*/

    }
    /*
    private void init(){

        productCategories.add(new ProductCategory("DairyProducts"));
        productCategories.add(new ProductCategory("Meat"));
        productCategories.add(new ProductCategory("Honey"));
        productCategories.add(new ProductCategory("Furniture"));

        productsService.addProduct(new PerishableWP(25.49f, "Grounded Beef", productCategories.get(1), MeasuringUnit.KG, 7));
        productsService.addProduct(new PerishableUP(4.5f, "Milk bottle", productCategories.get(0),3));
        productsService.addProduct(new UnitaryProduct(44.5f, "Wild Honey 450g", productCategories.get(2)));
        productsService.addProduct(new PerishableUP(28f, "Smoked pork ribs", productCategories.get(1),28));
        productsService.addProduct(new UnitaryProduct(140f, "Simple Chair", productCategories.get(3)));
        productsService.addProduct(new UnitaryProduct(450f, "Coffee table", productCategories.get(3)));

        distributorService.addDistributor(new Distributor("HLB", 0.08f));
        distributorService.addDistributor(new Distributor("La 45 de pasi", 0.1f));
        distributorService.addDistributor(new Distributor("Mini Image", 0.15f));

        List<Distributor> d = distributorService.getDistributors();
        d.get(0).addProduct(productsService.getProducts().get(0));
        d.get(0).addProduct(productsService.getProducts().get(2));
        d.get(0).addProduct(productsService.getProducts().get(3));


        d.get(1).addProduct(productsService.getProducts().get(1));
        d.get(1).addProduct(productsService.getProducts().get(4));

        d.get(2).addProduct(productsService.getProducts().get(5));
        d.get(2).addProduct(productsService.getProducts().get(1));

        stock.supplyProduct(distributorService.orderProductBatch(productsService.getProducts().get(3), 2f));
        stock.supplyProduct(distributorService.orderProductBatch(productsService.getProducts().get(1), 2f));
        stock.supplyProduct(distributorService.orderProductBatch(productsService.getProducts().get(4), 2f));

    }
    */

    private static void menu() {
        Scanner in = new Scanner(System.in);
        int opt;
        ProductService.removeExpiredBatches();

        while(true){

            System.out.println(" 1. Show all products sorted by name");
            System.out.println(" 2. Show all products sorted by category");
            System.out.println(" 3. Show all products sorted by price");
            System.out.println(" 4. Show all products in a category");
            System.out.println(" 5. Show all products in stock");
            System.out.println(" 6. Update product price");
            System.out.println(" 7. Create new product");
            System.out.println(" 8. Remove product");

            System.out.println(" 9. Supply a product");
            System.out.println("10. Retrieve products from stock");

            System.out.println("11. Show all categories");
            System.out.println("12. Rename a category");
            System.out.println("13. Create a new category");
            System.out.println("14. Delete a category (and all the products associated with that category)");

            System.out.println("15. Show all distributors");
            System.out.println("16. Show a distributor and his distributed products");
            System.out.println("17. Update distributor discount");
            System.out.println("18. Add distributed product for a distributor");
            System.out.println("19. Remove distributed product for a distributor");
            System.out.println("20. Add distributor");
            System.out.println("21. Remove distributor");

            System.out.println("0. Exit");
            opt = Integer.parseInt(in.nextLine());

            switch (opt) {
                case 1:{
                    ListingItems.print(ProductService.getAllProductsSortedByName());
                    break;
                }
                case 2:{
                    ListingItems.print(ProductService.getAllProductsSortedByCategory());
                    break;
                }
                case 3:{
                    ListingItems.print(ProductService.getAllProductsSortedByPrice());
                    break;
                }
                case 4:{
                    ProductCategory category = (ProductCategory) ListingItems.pick(ProductService.getAllCategories(), in);
                    ListingItems.print(ProductService.getProductsInCategory(category));
                    break;
                }
                case 5:{
                    ListingItems.print(ProductService.getProductsInStock());
                    break;
                }
                case 6:{
                    Product product = (Product) ListingItems.pick(ProductService.getAllProducts(), in);
                    if(product == null){
                        System.out.println("No Products to pick from.");
                    }
                    else{
                        System.out.println("Enter new price:");
                        float newPrice = in.nextFloat();
                        ProductService.updateProductPrice(product, newPrice);
                    }
                    break;
                }
                case 7:{
                    System.out.println("Enter product name:");
                    String name = in.nextLine();
                    System.out.println("Enter product price:");
                    float price = in.nextFloat();
                    ProductCategory category = (ProductCategory) ListingItems.pick(ProductService.getAllCategories(), in);
                    ProductService.addProduct(name, price, category.getCategoryId());
                    break;
                }
                case 8:{
                    Product product = (Product) ListingItems.pick(ProductService.getAllProducts(), in);
                    ProductService.removeProduct(product);
                    break;
                }
                case 9:{
                    Product product = (Product) ListingItems.pick(ProductService.getAllProducts(), in);
                    Distributor distributor = (Distributor) ListingItems.pick(DistributorService.getDistributorsForProduct(product), in);
                    System.out.println("Enter quantity (integer):");
                    int quantity = in.nextInt();
                    ProductService.addBatch(product, distributor, quantity);
                    break;
                }
                case 10:{
                    Product product = (Product) ListingItems.pick(ProductService.getProductsInStock(), in);
                    if(product == null)
                    {
                        System.out.println("No products in stock");
                        break;
                    }
                    List<ProductBatch> batches = ProductService.getProductBatches(product);
                    if(batches.size() == 0)
                    {
                        System.out.println("No products in stock");
                        break;
                    }
                    float s = 0;
                    s = batches.stream().map(batch -> batch.getQuantity()).reduce(0.f, Float::sum);
                    if(s == 0)
                    {
                        System.out.println("No products in stock");
                        break;
                    }
                    System.out.println("Enter desired quantity (should be an integer and smaller than or equal to " + s + ") :");
                    int q = in.nextInt();
                    if(q > s){
                        System.out.println("Not enough products in stock");
                        break;
                    }
                    batches.sort((x, y) -> {
                        if( y.getProductionDate().isAfter(x.getProductionDate()))
                            return 1;
                        if( x.getProductionDate().isAfter(y.getProductionDate()))
                            return -1;
                        return 0;
                    });

                    while(q > 0){
                        if(q >= batches.get(0).getQuantity()){
                            q -= batches.get(0).getQuantity();
                            ProductService.removeBatch(batches.get(0)); // delete from database;
                            batches.remove(0); // delete from memory as well;
                        }
                        else{
                            batches.get(0).setQuantity(batches.get(0).getQuantity() - q);
                            ProductService.updateBatchQuantity(batches.get(0), (int) batches.get(0).getQuantity());
                            q=0;
                        }
                    }
                    break;
                }

                case 11:{
                    ListingItems.print(ProductService.getAllCategories());
                    break;
                }
                case 12:{
                    ProductCategory category = (ProductCategory) ListingItems.pick(ProductService.getAllCategories(), in);
                    System.out.println("Enter new name for category " + category.getCategoryName() + ": ");
                    String name = in.nextLine();
                    ProductService.renameCategory(category, name);
                    break;
                }
                case 13:{
                    System.out.println("Enter a name for the new category: ");
                    String name = in.nextLine();
                    ProductService.addCategory(name);
                    break;
                }
                case 14:{
                    ProductCategory category = (ProductCategory) ListingItems.pick(ProductService.getAllCategories(), in);
                    ProductService.removeCategory(category);
                    break;
                }

                case 0:{
                    in.close();
                    System.out.println("Closing manager..."); // all changes are committed to the database so i can just close; no need for an extra save or something like that
                    return ;
                }
                default:{
                    System.out.println("IDK m8; try something else pls");
                }
            }

            System.out.println("Press enter to return to the menu");
            in.nextLine();
        }


    }


}
