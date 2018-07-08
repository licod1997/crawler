package com.pickyourcpu.service;

import com.pickyourcpu.entity.Product;
import com.pickyourcpu.entity.Shop;
import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.repository.ProductRepository;
import com.pickyourcpu.util.EntityToJAXB;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CompareServiceImpl implements CompareService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductsJAXB addComparingList( Long id, HttpServletRequest req ) {
        Product product = productRepository.findProductById( id );
        Collections.sort( product.getShops(), Comparator.comparing( Shop::getPrice ) );
        HttpSession session = req.getSession();
        ProductsJAXB productsJAXB = (ProductsJAXB) session.getAttribute( "COMPARE" );

        if ( product != null ) {
            if ( productsJAXB == null ) {
                productsJAXB = new ProductsJAXB();
                productsJAXB.getProduct().add( EntityToJAXB.parseProductToProductJAXB( product ) );
            } else {
                if ( productsJAXB.getProduct().stream().allMatch( item -> (long) item.getId() != product.getId() ) ) {
                    productsJAXB.getProduct().add( EntityToJAXB.parseProductToProductJAXB( product ) );
                }
            }
        }

        session.setAttribute( "COMPARE", productsJAXB );
        return productsJAXB;
    }

    @Override
    public ProductsJAXB removeComparingList( Long id, HttpServletRequest req ) {
        HttpSession session = req.getSession();
        ProductsJAXB productsJAXB = (ProductsJAXB) session.getAttribute( "COMPARE" );

        if ( productsJAXB == null ) return null;

        productsJAXB.getProduct().removeIf( item -> (long) item.getId() == id );
        session.setAttribute( "COMPARE", productsJAXB );

        return productsJAXB;
    }
}
