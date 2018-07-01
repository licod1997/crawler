package com.pickyourcpu.service;

import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import org.springframework.data.domain.Page;

public interface SearchListService {
    Page<Product> searchListPage( SearchCreateriaDTO searchCreateriaDTO );
}
