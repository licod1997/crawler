package com.pickyourcpu.controller;

import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestController {
    @Autowired
    private SearchService searchService;

    @GetMapping( value = "/goi-y-san-pham.xml",
            produces = "application/xml" )
    public ProductsJAXB suggestProduct( @RequestParam( value = "name", defaultValue = "" ) String name ) {
        return searchService.getTop5ProductNameLike( name );
    }

}
