import Product.ProductCategory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class cmpName implements Comparator<ProductCategory>{
    public int compare(ProductCategory c1, ProductCategory c2){
        return c1.getCategoryName().compareTo(c2.getCategoryName());
    }
}

public class Main {

    public static void main(String[] args) {

        ArrayList<ProductCategory> l = new ArrayList<ProductCategory>();

        l.add(new ProductCategory("ASD"));
        l.add(new ProductCategory("QWE"));
        l.add(new ProductCategory("ASQWE"));

        l.sort(new cmpName());

        System.out.println(l);

    }
}
