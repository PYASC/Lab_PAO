import Distributor.Distributor;
import Product.Product;
import Product.UnitaryProduct;
import Product.WeightedProduct;
import Product.PerishableUP;
import Product.PerishableWP;
import Product.MeasuringUnit;
import Product.ProductBatch;
import Product.ProductCategory;
import Services.DistributorService;
import Services.ProductService;

import java.util.*;


public class Store {
    public static void main(String[] args) {
        try {
            menu();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
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
            try{
                opt = Integer.parseInt(in.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Not a number; menu closing....");
                return;
            }

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
                    System.out.println("Choose a category for the new product:");
                    ProductCategory category = (ProductCategory) ListingItems.pick(ProductService.getAllCategories(), in);
                    if(category == null)
                        System.out.println("No category to choose from. How about adding one to the database first?");
                    else{
                        System.out.println("Enter product name:");
                        String name = in.nextLine();
                        System.out.println("Enter product price:");
                        float price = in.nextFloat();
                        Product p = new Product(0, price, name, category); // use id 0 to mark temporary objects;
                        System.out.println("Is the product sold as a unit?[y/n]"); in.nextLine();
                        String s = in.nextLine().trim();
                        if(s.equalsIgnoreCase("y"))
                            p = new UnitaryProduct(p);
                        else{
                            System.out.println("Choose the unit of measure:");
                            List<MeasuringUnit> l = new ArrayList<>(); l.add(MeasuringUnit.Kg); l.add(MeasuringUnit.L);
                            MeasuringUnit m = (MeasuringUnit) ListingItems.pick(l, in);
                            p = new WeightedProduct(p, m);
                        }

                        System.out.println("Is the product perishable?[y/n]");
                        s = in.nextLine().trim();
                        if(s.equalsIgnoreCase("y")){
                            System.out.println("Enter the lifespan of the product in number of days:");
                            int n = in.nextInt();
                            if(p instanceof UnitaryProduct)
                                p = new PerishableUP(p, n);
                            else
                                p = new PerishableWP(p, ((WeightedProduct) p).getMeasuringUnit(), n);
                        }
                        ProductService.addProduct(p);
                    }
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
                    if(distributor == null)
                        System.out.println("No distributors for the chosen products");
                    else{
                        System.out.println("Enter quantity (integer):");
                        int quantity = in.nextInt();
                        ProductService.addBatch(product, distributor, quantity);
                    }
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
                    if(category == null){
                        System.out.println("No categories registered in the database");
                    }
                    else {
                        ProductService.removeCategory(category);
                    }
                    break;
                }
                case 15:{
                    ListingItems.print(DistributorService.getAll());
                    break;
                }
                case 16:{
                    Distributor d = (Distributor) ListingItems.pick(DistributorService.getAll(), in);
                    if(d == null){
                        System.out.println("No distributors in database");
                    }
                    else{
                        System.out.println(d.present());
                    }
                    break;
                }
                case 17:{
                    Distributor d = (Distributor) ListingItems.pick(DistributorService.getAll(), in);
                    if(d == null){
                        System.out.println("No distributors in database");
                        break;
                    }
                    System.out.println("Enter new discount (float between 0 and 1)");
                    Float discount = in.nextFloat();
                    if(discount<0 || discount>1){
                        System.out.println("Invalid number; Better luck next time");
                    }
                    else{
                        DistributorService.updateDistributorDiscount(d, discount);
                    }
                    break;
                }
                case 18:{
                    Distributor d = (Distributor) ListingItems.pick(DistributorService.getAll(), in);
                    if(d == null){
                        System.out.println("No distributors in database");
                        break;
                    }
                    Product p = (Product) ListingItems.pick(ProductService.getUnownedProductsForDistributor(d), in);
                    if(p == null){
                        System.out.println("Can't add any new product for the chosen distributor");
                    }
                    else{
                        DistributorService.addProductForDistributor(d, p);
                    }
                    break;
                }
                case 19:{
                    Distributor d = (Distributor) ListingItems.pick(DistributorService.getAll(), in);
                    if(d == null){
                        System.out.println("No distributors in database");
                        break;
                    }
                    Product p = (Product) ListingItems.pick(ProductService.getProductsForDistributor(d), in);
                    if(p == null){
                        System.out.println("No products distributed for the chosen distributor");
                    }
                    else{
                        DistributorService.removeProductForDistributor(d, p);
                    }
                    break;
                }
                case 20:{
                    System.out.println("Enter distributor name:");
                    String name = in.nextLine();
                    System.out.println("Enter discount offered by the distributor (float between 0 and 1");
                    Float discount = in.nextFloat();
                    if(discount<0 || discount>1){
                        System.out.println("Invalid number; Better luck next time");
                    }
                    else{
                        DistributorService.addDistributor(name, discount);
                    }
                    break;
                }
                case 21:{
                    Distributor d = (Distributor) ListingItems.pick(DistributorService.getAll(), in);
                    if(d == null){
                        System.out.println("No distributors in database");
                    }
                    else{
                        DistributorService.removeDistributor(d);
                    }
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
