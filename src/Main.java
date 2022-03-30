import Product.*;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        ProductService.addCategory(new ProductCategory("DairyProducts"));
        ProductService.addCategory(new ProductCategory("Meat"));
        ProductService.addProduct(new Product(15.8f, "Chicken Wings", ProductService.getCategories().get(1)));
        ProductService.addProduct(new Product(20.25f, "Pork Ribs", ProductService.getCategories().get(1)));
        ProductService.addProduct(new Product(4.5f, "Milk", ProductService.getCategories().get(0)));


        menu();

        Product[] p = {new Product(100f, "Chair", new ProductCategory("Furniture")),
                new PerishableProduct(7.5f, "Milk", new ProductCategory("DairyProducts"), new Date())};

        System.out.println(Arrays.toString(p));

        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        String s = in.nextLine().trim();
        System.out.println(x);
        System.out.println(s);
    }

    public static void menu() {

        Scanner in = new Scanner(System.in);

        int opt;
        int index=0;
        float p=0;
        String s=null;

        while(true){

            System.out.println("1. Show products");
            System.out.println("2. Add Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Update Product Price");
            System.out.println("5. Sort Products by Name");
            System.out.println("6. Sort Products by Price");
            System.out.println("7. Sort Products by Category Name");
            System.out.println("Option: ");
            opt =  Integer.parseInt(in.nextLine());

            switch (opt) {
                case 1:
                    for(Product prod: ProductService.getProducts()){
                        System.out.println(prod);
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

                    ProductService.addProduct(new Product(p, s, ProductService.getCategories().get(index-1)));
                    break;
                case 3:
                    System.out.println("Index of product to be removed:");
                    index = Integer.parseInt(in.nextLine());
                    ProductService.removeProduct(index-1);
                    break;
                case 4:
                    System.out.println("Index of product:");
                    index = Integer.parseInt(in.nextLine());
                    System.out.println("New price of the product:");
                    p = Float.parseFloat(in.nextLine());
                    ProductService.updateProductPrice(index-1, p);
                    break;
                case 5:
                    ProductService.sortProductsByName();
                    break;
                case 6:
                    ProductService.sortProductsByPrice();
                    break;
                case 7:
                    ProductService.sortProductsByCategory();
                    break;
                default:
                    System.out.println("Unknown option");
            }

        }


    }


}
