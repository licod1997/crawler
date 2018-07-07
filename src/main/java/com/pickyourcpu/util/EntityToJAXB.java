package com.pickyourcpu.util;

import com.pickyourcpu.entity.Product;
import com.pickyourcpu.entity.Shop;
import com.pickyourcpu.jaxb.ProductJAXB;
import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.jaxb.ShopJAXB;
import com.pickyourcpu.jaxb.ShopsJAXB;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EntityToJAXB {

    public static ProductsJAXB parseListProductToProductsJAXB( List<Product> productList) {
        ProductsJAXB productsJAXB = new ProductsJAXB();

        for ( Product product : productList ) {
            ShopsJAXB shopsJAXB = new ShopsJAXB();

            for ( Shop shop : product.getShops() ) {
                shopsJAXB.getShop().add( new ShopJAXB(
                        shop.getId(),
                        shop.getUrl(),
                        shop.getPrice()
                ) );
            }

            Collections.sort( shopsJAXB.getShop(), Comparator.comparing( ShopJAXB::getPrice ) );

            ProductJAXB productJAXB = new ProductJAXB(
                    product.getId(),
                    product.getName(),
                    product.getBenchmark(),
                    product.getSocket(),
                    product.getClockspeed(),
                    product.getTurbospeed(),
                    product.getTDP(),
                    product.getNoOfCores(),
                    product.getCoresDescription(),
                    product.getDescription(),
                    shopsJAXB
            );
            productsJAXB.getProduct().add( productJAXB );
        }

        return productsJAXB;
    }
}
