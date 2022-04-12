import Client.Client;
import Distributor.Distributor;
import Distributor.IDistributorService;
import Distributor.DistributorService;
import Product.Product;
import Product.ProductBatch;
import Product.UnitaryProduct;
import Product.WeightedProduct;
import Product.PerishableUP;
import Product.PerishableWP;
import Product.ProductCategory;
import Product.MeasuringUnit;
import Product.IProductService;
import Product.ProductService;
import Product.IStock;
import Product.Stock;

import java.util.*;


public class Store {
    private final List<ProductCategory> productCategories;
    private final IProductService productsService;
    private final IDistributorService distributorService;
    private final IStock stock;
    //private final List<Client> clients;

    private static Store store = null;

    private Store() {
        this.productCategories = new ArrayList<ProductCategory>();
        this.productsService = new ProductService();
        this.distributorService = new DistributorService();
        this.stock = new Stock();
        //this.clients = new ArrayList<Client>();
    }

    public static Store getInstance(){
        if(Store.store == null)
            Store.store = new Store();
        return store;
    }
    public static void main(String[] args) {

        getInstance();
        store.init();
        /*System.out.println(store.productsService.getProducts());
        System.out.println(store.productCategories);
        System.out.println(store.distributorService.getDistributors());
        System.out.println(store.stock.getProductsInStock());

        Product p = (Product) ListingItems.pick(store.productsService.getProducts());
        System.out.println("Picked Product: " + p.toString());*/

        try {
            store.menu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally { /// used for saving to csv and db later
            //Store.saveCSV();
            //Store.saveDB();
        }*/



    }

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

    private void menu() {

        Scanner in = new Scanner(System.in);

        int opt, d;
        int index;
        float p;
        String s;

        while(true){

            System.out.println("1. Show all products sorted by name");
            System.out.println("2. Show all products sorted by category name");
            System.out.println("3. Show all products sorted by price");
            System.out.println("4. Show products in stock");
            System.out.println("5. Supply a product");
            System.out.println("6. Retrieve products from stock");
            System.out.println("7. Exit");
            opt = Integer.parseInt(in.nextLine());

            switch (opt) {
                case 1:{
                    ListingItems.print(this.productsService.getProductsSortedByName());
                    break;
                }
                case 2:{
                    ListingItems.print(this.productsService.getProductsSortedByCategory());
                    break;
                }
                case 3:{
                    ListingItems.print(this.productsService.getProductsSortedByPrice());
                    break;
                }
                case 4:{
                    ListingItems.print(this.stock.getProductsInStock());
                    break;
                }
                case 5:{
                    System.out.println("Pick the product you wish to be supplied");
                    Product prod = (Product) ListingItems.pick(productsService.getProducts(), in);
                    System.out.println("Enter a quantity for the chosen product (make sure it matches the product type)");
                    p = Float.parseFloat(in.nextLine());
                    ProductBatch newBatch = distributorService.orderProductBatch(prod, p);
                    stock.supplyProduct(newBatch);

                    break;
                }
                case 6:{
                    System.out.println("Pick the product you wish to be supplied");
                    Product prod = (Product) ListingItems.pick(stock.getProductsInStock(), in);
                    System.out.println("Enter a quantity for the chosen product (make sure it matches the product type)");
                    p = Float.parseFloat(in.nextLine());
                    if(stock.retrieveProductQuantity(prod, p))
                        System.out.println("Quantity retrieved successfully");
                    else
                        System.out.println("Couldn't retrieve the given quantity");

                    break;
                }
                case 7:{
                    in.close();
                    return;
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
