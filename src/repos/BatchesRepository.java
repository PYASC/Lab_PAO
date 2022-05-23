package repos;

import Entities.DistributorEntity;
import Entities.ProductBatchEntity;
import Entities.UnitaryProductEntity;
import Exceptions.DataBaseException;
import Mappers.Mappers;

import java.util.List;

public class BatchesRepository {

    public static ProductBatchEntity getById(int batchId){
        String sql = "select * from product_batches where batchId = " + batchId;
        List<ProductBatchEntity> result = QueryExecutor.executeReadQuery(sql, Mappers::ProductBatchMapper);

        if(result.isEmpty())
            return null;
        if(result.size()>1)
            throw new DataBaseException("Primary key constraint violation!");
        return result.get(0);

    }

    public static List<ProductBatchEntity> getAll(){
        String sql = "select * from product_batches";
        return QueryExecutor.executeReadQuery(sql, Mappers::ProductBatchMapper);
    }

    public static List<ProductBatchEntity> getAllByProductId(int productId){
        String sql = "select * from product_batches where productId = " + productId;
        return QueryExecutor.executeReadQuery(sql, Mappers::ProductBatchMapper);
    }

    public static List<ProductBatchEntity> getAllByDistributorId(int distributorId){
        String sql = "select * from product_batches where distributorId = " + distributorId;
        return QueryExecutor.executeReadQuery(sql, Mappers::ProductBatchMapper);
    }



}
