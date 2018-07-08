package com.pickyourcpu.repository;

import com.pickyourcpu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query( value = "select distinct no_of_cores from product", nativeQuery = true )
    List<Integer> findDistinctNoOfCores();

    @Query( value = "select distinct socket from product", nativeQuery = true )
    List<String> findDistinctSocket();

    Product findProductByName( String name );

    Product findProductById( Long id );

    List<Product> findTop10ByNameLike( String name );

    default List<Product> saveListProducts( List<Product> products ) {
        List<Product> result = new ArrayList<>();

        if ( products == null ) {
            return result;
        }

        for ( Product product : products ) {
            Product dbProduct = findProductByName( product.getName() );
            if ( dbProduct == null ) {
                result.add( save( product ) );
            } else {
                dbProduct.setBenchmark( product.getBenchmark() );
                dbProduct.setClockspeed( product.getClockspeed() );
                dbProduct.setTurbospeed( product.getTurbospeed() );
                dbProduct.setNoOfCores( product.getNoOfCores() );
                dbProduct.setCoresDescription( product.getCoresDescription() );
                dbProduct.setTDP( product.getTDP() );
                dbProduct.setDescription( product.getDescription() );
                dbProduct.setCoresDescription( product.getCoresDescription() );
//                dbProduct.setShops( product.getShops() );
                result.add( save( dbProduct ) );
            }
        }
        return result;
    }
}
