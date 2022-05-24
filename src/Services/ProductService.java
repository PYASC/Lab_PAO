package Services;

import Distributor.Distributor;
import Entities.*;
import repos.BatchesRepository;
import repos.ProductRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import  Product.*;


public class ProductService {

    public static ProductCategory getCategory(int categoryId){
        CategoryEntity cat = ProductRepository.getCategoryById(categoryId);
        if(cat == null)
            return null;
        return new ProductCategory(cat.getCategoryId(), cat.getCategoryName());
    }

    public static List<ProductCategory> getAllCategories(){
        List<ProductCategory> categories = new ArrayList<>();
        for(CategoryEntity cat: ProductRepository.getAllCategories()){
            if(cat != null)
                categories.add(new ProductCategory(cat.getCategoryId(), cat.getCategoryName()));
        }
        return categories;
    }

    public static int renameCategory(ProductCategory category, String newName){
        return ProductRepository.renameCategory(category.getCategoryId(), newName);
    }

    public static int addCategory(String name){
        return ProductRepository.addCategory(name);
    }

    public static int removeCategory(ProductCategory category){
        return ProductRepository.deleteCategory(category.getCategoryId());
    }

    private static List<Product> mapEntityToProduct(List<UnitaryProductEntity> l){
        List<Product> products = new ArrayList<Product>();
        Map<Integer, ProductCategory> categories = new HashMap<Integer, ProductCategory>();

        for(UnitaryProductEntity p: l){
            Product product = new Product(p.getProductId(), p.getProductPrice(), p.getProductName());

            int catId = p.getCategoryId();
            if(categories.containsKey(catId)){
                product.setCategory(categories.get(catId));
            }
            else {
                ProductCategory newCat = getCategory(catId);
                categories.put(catId, newCat);
                product.setCategory(newCat);
            }
            products.add(product);
        }

        return products;
    }

    private static List<UnitaryProduct> mapEntityToUP(List<UnitaryProductEntity> l){
        List<UnitaryProduct> products = new ArrayList<UnitaryProduct>();
        Map<Integer, ProductCategory> categories = new HashMap<Integer, ProductCategory>();

        for(UnitaryProductEntity p: l){
            UnitaryProduct product = new UnitaryProduct(p.getProductId(), p.getProductPrice(), p.getProductName());

            int catId = p.getCategoryId();
            if(categories.containsKey(catId)){
                product.setCategory(categories.get(catId));
            }
            else {
                ProductCategory newCat = getCategory(catId);
                categories.put(catId, newCat);
                product.setCategory(newCat);
            }
            products.add(product);
        }

        return products;
    }

    private static List<PerishableUP> mapEntityToPerishableUP(List<PerishableUnitaryProductEntity> l){
        List<PerishableUP> products = new ArrayList<PerishableUP>();
        Map<Integer, ProductCategory> categories = new HashMap<Integer, ProductCategory>();

        for(PerishableUnitaryProductEntity p: l){
            PerishableUP product = new PerishableUP(p.getProductId(), p.getProductPrice(), p.getProductName(), p.getLifespan());
            int catId = p.getCategoryId();
            if(categories.containsKey(catId)){
                product.setCategory(categories.get(catId));
            }
            else {
                ProductCategory newCat = getCategory(catId);
                categories.put(catId, newCat);
                product.setCategory(newCat);
            }
            products.add(product);
        }

        return products;
    }

    private static List<WeightedProduct> mapEntityToWP(List<WeightedProductEntity> l){
        List<WeightedProduct> products = new ArrayList<WeightedProduct>();
        Map<Integer, ProductCategory> categories = new HashMap<Integer, ProductCategory>();

        for(WeightedProductEntity p: l){
            WeightedProduct product = new WeightedProduct(p.getProductId(), p.getProductPrice(), p.getProductName(), p.getUnit());

            int catId = p.getCategoryId();
            if(categories.containsKey(catId)){
                product.setCategory(categories.get(catId));
            }
            else {
                ProductCategory newCat = getCategory(catId);
                categories.put(catId, newCat);
                product.setCategory(newCat);
            }
            products.add(product);
        }

        return products;
    }


    private static List<PerishableWP> mapEntityToPerishableWP(List<PerishableWeightedProductEntity> l){
        List<PerishableWP> products = new ArrayList<PerishableWP>();
        Map<Integer, ProductCategory> categories = new HashMap<Integer, ProductCategory>();

        for(PerishableWeightedProductEntity p: l){
            PerishableWP product = new PerishableWP(p.getProductId(), p.getProductPrice(), p.getProductName(), p.getUnit(), p.getLifespan());

            int catId = p.getCategoryId();
            if(categories.containsKey(catId)){
                product.setCategory(categories.get(catId));
            }
            else {
                ProductCategory newCat = getCategory(catId);
                categories.put(catId, newCat);
                product.setCategory(newCat);
            }
            products.add(product);
        }

        return products;
    }

    public static Product getProduct(int id){
        UnitaryProductEntity upe = ProductRepository.getById(id);
        if(upe == null)
            return null;
        return new Product(upe.getProductId(), upe.getProductPrice(), upe.getProductName());
    }

    public static List<Product> getAllProducts(){
        return mapEntityToProduct(ProductRepository.getAll());
    }

    public static List<Product> getAllProductsSortedByName(){
        return getAllProducts().stream().sorted((prod1, prod2) -> prod1.getProductName().compareTo(prod2.getProductName()))
                .collect(Collectors.toList());
    }

    public static List<Product> getAllProductsSortedByPrice(){
        return getAllProducts().stream().sorted((prod1, prod2) -> Float.compare(prod1.getPrice(), prod2.getPrice()))
                .collect(Collectors.toList());
    }

    public static List<Product> getAllProductsSortedByCategory(){
        return getAllProducts().stream().sorted((prod1, prod2) -> prod1.getCategory().compareTo(prod2.getCategory()))
                .collect(Collectors.toList());
    }

    public static List<UnitaryProduct> getAllUP(){
        return mapEntityToUP(ProductRepository.getAllUPE());
    }

    public static List<WeightedProduct> getAllWP(){
        return mapEntityToWP(ProductRepository.getAllWPE());
    }

    public static List<PerishableUP> getAllPerishableUP(){
        return mapEntityToPerishableUP(ProductRepository.getAllPerishableUPE());
    }

    public static List<PerishableWP> getAllPerishableWP(){
        return mapEntityToPerishableWP(ProductRepository.getAllPerishableWPE());
    }

    public static List<Product> getProductsInCategory(ProductCategory category){
        return mapEntityToProduct(ProductRepository.getAllByCategoryId(category.getCategoryId()));
    }

    public static List<Product> getProductsInStock(){
        List<Product> products = new ArrayList<>();
        for(int id: ProductRepository.getProductsInStock()){
            Product p = getProduct(id);
            if(p != null)
                products.add(p);
        }

        return products;
    }


    public static int addProduct(String name, float price, int categoryId){
        return ProductRepository.addProduct(name, price, categoryId);
    }

    public static int updateProductPrice(Product p, float newPrice){
        return ProductRepository.updateProductPrice(p.getProductId(), newPrice);
    }

    public static int removeProduct(Product p){
        return ProductRepository.removeProduct(p.getProductId());
    }

    private static LocalDate mapStringToDate(String date){
        String[] asd = date.split("-");

        if(asd.length == 3)
            return LocalDate.of( Integer.parseInt(asd[0]), Integer.parseInt(asd[1]), Integer.parseInt(asd[2]));

        return null;
    }

    private static List<ProductBatch> mapEntityToPB(List<ProductBatchEntity> l){
        List<ProductBatch> result = new ArrayList<>();
        for(ProductBatchEntity pbe: l){
            ProductBatch pb;
            int id = pbe.getBatchId();
            Product p = ProductService.getProduct(pbe.getProductId());
            String date = pbe.getProductionDate();
            float quantity = pbe.getQuantity();
            int distributorId = pbe.getDistributorId();
            Distributor d = DistributorService.getDistributorById(pbe.getDistributorId());

            pb = new ProductBatch(id, p, mapStringToDate(date), quantity, d);
            result.add(pb);
        }
        return result;
    }

    public static List<ProductBatch> getProductBatches(Product p){
        return mapEntityToPB(BatchesRepository.getAllByProductId(p.getProductId()));
    }

    public static List<ProductBatch> getAllBatches(){
        return mapEntityToPB(BatchesRepository.getAll());
    }

    public static void removeExpiredBatches(){
        for(ProductBatch pb: getAllBatches()){
            if(pb.isExpired()){
                BatchesRepository.removeBatch(pb.getId());
            }
        }
    }

    public static int addBatch(Product p, Distributor d, float quantity){
        return BatchesRepository.addBatch(p.getProductId(), d.getId(), quantity, LocalDate.now().toString());
    }

    public static int removeBatch(ProductBatch pb){
        return BatchesRepository.removeBatch(pb.getId());
    }

    public static int updateBatchQuantity(ProductBatch pb, int newQuantity){
        return BatchesRepository.updateBatchQuantity(pb.getId(), newQuantity);
    }

    public static void main(String[] args){
        System.out.println(LocalDate.now().toString());
    }

}
