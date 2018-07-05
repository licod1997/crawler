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

        int pageNum = searchCreateriaDTO.getPage() - 1;
        int size = searchCreateriaDTO.getMaxSize();
        Sort.Direction sort = Sort.Direction.ASC;
        if ( searchCreateriaDTO.getSort().equals( "DESC" ) ) sort = Sort.Direction.DESC;
        String field = searchCreateriaDTO.getField();

        Pageable pageable = new PageRequest( pageNum, size, sort, field );
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
}
