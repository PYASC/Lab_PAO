package repos;

import Entities.*;
import Exceptions.DataBaseException;
import Mappers.Mappers;

import java.sql.SQLException;
import java.util.List;

public class ProductRepository {

    public static UnitaryProductEntity getById(int productId){
        String sql = "select * from products where productId = " + productId;

        List<UnitaryProductEntity> result = QueryExecutor.executeReadQuery(sql, Mappers::UPEMapper);
        if(result.isEmpty())
            return null;

        if(result.size() != 1)
            throw new DataBaseException("Primary key constraint violation!");

        return result.get(0);
    }

    public static List<UnitaryProductEntity> getAll(){
        String sql = "select * from products";
        return QueryExecutor.executeReadQuery(sql, Mappers::UPEMapper);
    }

    public static List<UnitaryProductEntity> getAllByCategoryId(int categoryId){
        String sql = "select * from products where categoryId = " + categoryId;
        return QueryExecutor.executeReadQuery(sql, Mappers::UPEMapper);
    }

    public static List<Integer> getProductsInStock(){
        String sql = "select distinct productId  from product_batches";
        return QueryExecutor.executeReadQuery(sql, resultSet -> {
            try{
                return resultSet.getInt("productId");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return null;
        });
    }

    public static List<UnitaryProductEntity> getAllUPE(){
        String sql = "select *  from unitary_products up join products p on up.productId = p.productId ";
        return QueryExecutor.executeReadQuery(sql, Mappers::UPEMapper);
    }

    public static List<WeightedProductEntity> getAllWPE(){
        String sql = "select *  from weighted_products wp join products p on wp.productId = p.productId ";
        return QueryExecutor.executeReadQuery(sql, Mappers::WPEMapper);
    }

    public static List<PerishableUnitaryProductEntity> getAllPerishableUPE(){
        String sql = "select *  from perishable_unitary_products pup join products p on pup.productId = p.productId ";
        return QueryExecutor.executeReadQuery(sql, Mappers::PerishableUPEMapper);
    }
    public static List<PerishableWeightedProductEntity> getAllPerishableWPE(){
        String sql = "select *  from perishable_weighted_products pwp join products p on pwp.productId = p.productId ";
        return QueryExecutor.executeReadQuery(sql, Mappers::PerishableWPEMapper);
    }

    public static int removeProduct(int productId){
        String sql = "delete from products where productId = " + productId;
        return QueryExecutor.executeUpdateQuery(sql);
    }

    public static int updateProductPrice(int productId, float newPrice){
        String sql = "update products set productPrice = " + newPrice + " where productId = "+productId;
        return QueryExecutor.executeUpdateQuery(sql);
    }

    public static List<Integer> getDistributorIdsForProduct(int productId){
        String sql = "select distributorId from distributed_products where productId = " + productId;
        return QueryExecutor.executeReadQuery(sql, resultSet -> {
            try {
                return resultSet.getInt("distributorId");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return 0;
        });
    }

    public static int addProduct(String productName, float productPrice, int categoryId){
        String sql = "insert into products(productName, productPrice, categoryId) values ('" + productName + "', " + productPrice + ", " + categoryId + ")";
        QueryExecutor.executeUpdateQuery(sql);
        sql = "select productId from products where productId = (select max(productId) from products)";
        return QueryExecutor.executeReadQuery(sql, resultSet -> {
            try {
                return (Integer) resultSet.getInt("productId");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }).get(0);
    }

    public static int addUPE(int productId){
        String sql = "insert into unitary_products(productID) values (" + productId + ")";
        return QueryExecutor.executeUpdateQuery(sql);
    }
    public static int addPerishableUPE(int productId, int lifespan){
        String sql = "insert into perishable_unitary_products(productID, lifespan) values (" + productId + ", " + lifespan + ")";
        return QueryExecutor.executeUpdateQuery(sql);
    }
    public static int addWPE(int productId, String unit){
        String sql = "insert into weighted_products(productID, unit) values (" + productId + ", '" + unit + "')";
        return QueryExecutor.executeUpdateQuery(sql);
    }
    public static int addPerishableWPE(int productId, String unit, int lifespan){
        String sql = "insert into perishable_weighted_products(productID, unit, lifespan) values (" + productId + ", '" + unit + "', " + lifespan + ")";
        return QueryExecutor.executeUpdateQuery(sql);
    }


    public static CategoryEntity getCategoryById(int categoryId){
        String sql = "select *  from categories where categoryId = " + categoryId;
        List<CategoryEntity> result = QueryExecutor.executeReadQuery(sql, Mappers::CategoryMapper);

        if(result.isEmpty())
            return null;

        if(result.size()>1)
            throw new DataBaseException("Primary key constraint violation!");

        return result.get(0);

    }

    public static List<CategoryEntity> getAllCategories(){
        String sql = "select *  from categories";
        return QueryExecutor.executeReadQuery(sql, Mappers::CategoryMapper);
    }

    public static int addCategory(String name){
        String sql = "insert into categories(categoryName) values ('" + name + "')";
        return QueryExecutor.executeUpdateQuery(sql);
    }

    public static int renameCategory(int categoryId, String name){
        String sql = "update categories set categoryName = '" + name + "' where categoryId = " + categoryId;
        return QueryExecutor.executeUpdateQuery(sql);
        // if return >1 could throw exception again, but it's not that helpful after all;
        // also it shouldn't be possible since it has PK restriction in the database
    }

    public static int deleteCategory(int categoryId){ // not recommended; will delete all products that have the given categoryId
        String sql = "delete from categories where categoryId = " + categoryId;
        return QueryExecutor.executeUpdateQuery(sql);
    }

}
