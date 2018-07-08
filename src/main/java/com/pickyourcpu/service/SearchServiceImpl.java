package com.pickyourcpu.service;

import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import com.pickyourcpu.entity.Shop;
import com.pickyourcpu.jaxb.ProductJAXB;
import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.repository.ProductRepository;
import com.pickyourcpu.repository.spec.ProductSpecification;
import com.pickyourcpu.util.EntityToJAXB;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchListPage( SearchCreateriaDTO searchCreateriaDTO ) {
        ProductSpecification spec = new ProductSpecification();
        spec.setCriteria( searchCreateriaDTO );

        int pageNum = searchCreateriaDTO.getPage() - 1;
        int size = searchCreateriaDTO.getMaxSize();

        Pageable pageable = new PageRequest( pageNum, size );
        Page<Product> page = productRepository.findAll( spec, pageable );
        return page;
    }

    @Override
    public List<Integer> getNoOfCoresList() {
        return productRepository.findDistinctNoOfCores();
    }

    @Override
    public List<String> getSocketList() {
        return productRepository.findDistinctSocket();
    }

    @Override
    public Product getProductDetail( Long id ) {
        Product product = productRepository.findProductById( id );
        Collections.sort( product.getShops(), Comparator.comparing( Shop::getPrice ) );
        return product;
    }

    @Override
    public ProductsJAXB getTop10ProductNameLike( String name ) {
        if ( StringUtils.isBlank( name ) ) return new ProductsJAXB();
        return EntityToJAXB.parseListProductToProductsJAXB( productRepository.findTop10ByNameLike( "%" + name + "%" ) );
    }
}
