package repos;

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

    public static void updateDistributorDiscount(int distributorId, float newDiscount){
        String sql = "update distributor set discount = " + newDiscount + " where distributorId = " + distributorId;
        QueryExecutor.executeUpdateQuery(sql);
    }

    public static void deleteDistributor(int distributorId){
        String sql = "delete from distributors where distributorId = " + distributorId;
        QueryExecutor.executeUpdateQuery(sql);
    }

    public static boolean addDistributor(String name, float discount){
        String sql = "insert into distributors(distributorName, discount) values ('" + name + "', " + discount + ")";
        if(QueryExecutor.executeUpdateQuery(sql) > 0) // insert succeeded
            return true;

        return false;
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
