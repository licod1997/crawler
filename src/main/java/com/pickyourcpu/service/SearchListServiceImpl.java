package com.pickyourcpu.service;

import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import com.pickyourcpu.repository.ProductRepository;
import com.pickyourcpu.repository.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchListServiceImpl implements SearchListService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> searchListPage( SearchCreateriaDTO searchCreateriaDTO ) {
        ProductSpecification spec = new ProductSpecification();
        spec.setCriteria( searchCreateriaDTO );
        Pageable pageable = new PageRequest( 1, 2, Sort.Direction.DESC, "benchmark" );
        Page<Product> page = productRepository.findAll( spec, pageable );
        return page;
    }
}
