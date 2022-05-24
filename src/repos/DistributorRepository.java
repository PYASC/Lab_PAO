package repos;

import Distributor.Distributor;
import Entities.DistributorEntity;
import Entities.DistributorProductEntity;
import Exceptions.DataBaseException;
import Mappers.Mappers;

import java.sql.SQLException;
import java.util.List;

public class DistributorRepository {

    public static DistributorEntity getById(int distributorId){
        String sql = "select * from distributors where distributorId = " + distributorId;
        List<DistributorEntity> result = QueryExecutor.executeReadQuery(sql, Mappers::DistributorMapper);

        if(result.isEmpty())
            return null;
        if(result.size()>1)
            throw new DataBaseException("Primary key constraint violation!");
        return result.get(0);

    }

    public static List<DistributorEntity> getAll(){
        String sql = "select * from distributors";
        return QueryExecutor.executeReadQuery(sql, Mappers::DistributorMapper);
    }

    public static List<DistributorEntity> getDistributorsForProduct(int productId){
        String sql = "select * from distributors ds where exists (select * from distributed_products dp where ds.distributorId = dp.distributorId";
        sql += "and dp.productId = " + productId + ")";
        return QueryExecutor.executeReadQuery(sql, Mappers::DistributorMapper);
    }

    public static int updateDistributorDiscount(int distributorId, float newDiscount){
        String sql = "update distributor set discount = " + newDiscount + " where distributorId = " + distributorId;
        return QueryExecutor.executeUpdateQuery(sql);
    }

    public static int deleteDistributor(int distributorId){
        String sql = "delete from distributors where distributorId = " + distributorId;
        return QueryExecutor.executeUpdateQuery(sql);
    }

    public static int addDistributor(String name, float discount){
        String sql = "insert into distributors(distributorName, discount) values ('" + name + "', " + discount + ")";
        return  QueryExecutor.executeUpdateQuery(sql);
    }

    public static List<Integer> getProductIdsForDistributor(int distributorId){
        String sql = "select productId from distributed_products where distributorId = " + distributorId;
        return QueryExecutor.executeReadQuery(sql, resultSet -> {   try {
                                                                        return resultSet.getInt("productId");
                                                                    }
                                                                    catch (SQLException e){
                                                                        e.printStackTrace();
                                                                    }
                                                                    return 0;
                                                                });
    }

    public static int addProductForDistributor(int distributorId, int productId){
        String sql = "insert into distributed_products(distributorId, productId) values (" + distributorId + ", " + productId + ")";
        return QueryExecutor.executeUpdateQuery(sql);
    }

    public static int removeProductForDistributor(int distributorId, int productId){
        String sql = "delete from distributed_products where distributorId = " + distributorId + " and productId = " + productId;
        return QueryExecutor.executeUpdateQuery(sql);
    }

}
