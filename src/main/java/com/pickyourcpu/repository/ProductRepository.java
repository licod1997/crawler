package com.pickyourcpu.repository;

import com.pickyourcpu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product findProductByName( String name );

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
                result.add( save( dbProduct ));
            }
        }
        return result;
    }
}
