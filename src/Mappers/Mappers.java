package Mappers;

import Entities.*;
import Product.MeasuringUnit;

import java.sql.ResultSet;
import java.sql.SQLException;


// helper class containing mappers for all DB entities;
// mappers won't throw SQLException; instead they return a null pointer;
public abstract class Mappers {

    public static UnitaryProductEntity UPEMapper(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("productId");
            String name = resultSet.getString("productName");
            float price = resultSet.getFloat("productPrice");
            int category = resultSet.getInt("categoryId");

            return new UnitaryProductEntity(id, name, price, category);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static PerishableUnitaryProductEntity PerishableUPEMapper(ResultSet resultSet) {
        UnitaryProductEntity p = UPEMapper(resultSet);
        int lifespan=0;
        try {
            lifespan = resultSet.getInt("lifespan");
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        if(p==null)
            return null;

        return new PerishableUnitaryProductEntity(p, lifespan);
    }

    public static WeightedProductEntity WPEMapper(ResultSet resultSet) {
        MeasuringUnit x;
        UnitaryProductEntity p = UPEMapper(resultSet);
        try{
            x = MeasuringUnit.valueOf(resultSet.getString("unit"));
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        if(p==null)
            return null;

        return new WeightedProductEntity(p, x);
    }

    public static PerishableWeightedProductEntity PerishableWPEMapper(ResultSet resultSet) {
        WeightedProductEntity p = WPEMapper(resultSet);
        int lifespan=0;
        try{
            lifespan = resultSet.getInt("lifespan");
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        if(p==null)
            return null;

        return new PerishableWeightedProductEntity(p, lifespan);
    }

    public static CategoryEntity CategoryMapper(ResultSet resultSet){
        try {
            int id = resultSet.getInt("categoryId");
            String name = resultSet.getString("categoryName");
            return new CategoryEntity(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static DistributorEntity DistributorMapper(ResultSet resultSet){
        try {
            int id = resultSet.getInt("distributorId");
            String name = resultSet.getString("distributorName");
            float discount = resultSet.getFloat("discount");
            return new DistributorEntity(id, name, discount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static DistributorProductEntity DPMapper(ResultSet resultSet){
        try {
            int distributorId = resultSet.getInt("distributorId");
            int productId = resultSet.getInt("productId");
            return new DistributorProductEntity(distributorId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ProductBatchEntity ProductBatchMapper(ResultSet resultSet){
        try {
            int id = resultSet.getInt("batchId");
            int productId = resultSet.getInt("productId");
            int distributorId = resultSet.getInt("distributorId");
            float quantity = resultSet.getFloat("quantity");
            String productionDate = resultSet.getString("production_date");
            return new ProductBatchEntity(id, productId, distributorId,quantity, productionDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
