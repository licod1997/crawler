package com.pickyourcpu.service;

import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import com.pickyourcpu.jaxb.ProductsJAXB;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService {
    Page<Product> searchListPage( SearchCreateriaDTO searchCreateriaDTO );

    List<Integer> getNoOfCoresList();

    List<String> getSocketList();

    Product getProductDetail( Long id );

    ProductsJAXB getTop10ProductNameLike( String name );
}
