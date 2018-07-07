package com.pickyourcpu.service;

import com.pickyourcpu.entity.Product;
import com.pickyourcpu.entity.Shop;
import com.pickyourcpu.repository.ProductRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CompareServiceImpl implements CompareService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getComparomgList( Long id, HttpServletRequest req ) {
        Product product = productRepository.findProductById( id );
        Collections.sort( product.getShops(), Comparator.comparing( Shop::getPrice ) );
        HttpSession session = req.getSession();
        List<Product> list = (List<Product>) session.getAttribute( "COMPARE" );

        if ( product != null ) {
            if ( list == null ) {
                list = new ArrayList<>();
                list.add( product );
            } else {
                if ( list.stream().allMatch( item -> (long) item.getId() != product.getId() )) {
                    list.add( product );
                }
            }
        }


        session.setAttribute( "COMPARE", list );
        return list;
    }
}
