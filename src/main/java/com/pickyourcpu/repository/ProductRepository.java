package com.pickyourcpu.repository;

import com.pickyourcpu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    default List<Product> saveListProducts( List<Product> products ) {
        List<Product> result = new ArrayList<>();

        if ( products == null ) {
            return result;
        }

        for ( Product product : products ) {
            result.add( save( product ) );
        }

        return result;
    }
}
