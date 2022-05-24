package Services;

import Distributor.Distributor;
import Entities.DistributorEntity;
import Product.Product;
import repos.DistributorRepository;

import java.util.ArrayList;
import java.util.List;

public class DistributorService {

    private static List<Distributor> mapEntityToDistributor(List<DistributorEntity> l){
        List<Distributor> distributors = new ArrayList<>();

        for(DistributorEntity de: l){
            Distributor d = new Distributor(de.getDistributorId(), de.getDistributorName(), de.getDiscount());
            List<Integer> productIds = DistributorRepository.getProductIdsForDistributor(d.getId());
            for(Integer prodId: productIds){
                d.addProduct(ProductService.getProduct(prodId));
            }
            distributors.add(d);
        }

        return distributors;
    }

    public static List<Distributor> getAll(){
        return  mapEntityToDistributor(DistributorRepository.getAll());
    }

    public static Distributor getDistributorById(int distributorID){
        DistributorEntity de = DistributorRepository.getById(distributorID);
        if(de == null)
            return null;
        Distributor d = new Distributor(de.getDistributorId(), de.getDistributorName(), de.getDiscount());

        List<Integer> productIds = DistributorRepository.getProductIdsForDistributor(d.getId());
        for(Integer prodId: productIds){
            d.addProduct(ProductService.getProduct(prodId));
        }

        return d;
    }

    public static List<Distributor> getDistributorsForProduct(Product p){
        return mapEntityToDistributor(DistributorRepository.getDistributorsForProduct(p.getProductId()));
    }

    public static int addDistributor(String name, float discount){
        return DistributorRepository.addDistributor(name, discount);
    }

    public static int updateDistributorDiscount(Distributor d, float newDiscount){
        return DistributorRepository.updateDistributorDiscount(d.getId(), newDiscount);
    }

    public static int addProductForDistributor(Distributor d, Product p){
        return DistributorRepository.addProductForDistributor(d.getId(), p.getProductId());
    }

    public static int removeProductForDistributor(Distributor d, Product p){
        return DistributorRepository.removeProductForDistributor(d.getId(), p.getProductId());
    }

    public static int removeDistributor(Distributor d){
        return DistributorRepository.deleteDistributor(d.getId());
    }

}
