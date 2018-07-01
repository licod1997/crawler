package com.pickyourcpu.controller;

import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Products;
import com.pickyourcpu.service.SearchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {

    @Autowired
    private SearchListService searchListService;

    @PostMapping( value = "/tim-kiem-danh-sach",
            consumes = "application/json",
            produces = "application/xml" )
    public Products searchList( @RequestBody SearchCreateriaDTO searchCreateriaDTO ) {
        Page page = searchListService.searchListPage( searchCreateriaDTO );
        Products products = new Products();
        products.getProduct().addAll( page.getContent() );
        return products;
    }
}
