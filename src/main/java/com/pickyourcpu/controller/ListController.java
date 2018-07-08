package com.pickyourcpu.controller;

import com.pickyourcpu.dto.ProductPageResponseDTO;
import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.service.SearchService;
import com.pickyourcpu.util.EntityToJAXB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ListController {

    @Autowired
    private SearchService searchService;

    @GetMapping( value = {"/danh-sach-san-pham", "/"} )
    public ModelAndView getListPage( ModelAndView mv ) {
        mv.setViewName( "danh-sach-san-pham" );
        mv.addObject( "socketList", searchService.getSocketList() );
        mv.addObject( "noOfCoresList", searchService.getNoOfCoresList() );
        return mv;
    }

    @PostMapping( value = "/danh-sach-san-pham.xml",
            consumes = "application/json",
            produces = "application/xml" )
    public ProductPageResponseDTO searchListXML( @RequestBody SearchCreateriaDTO searchCreateriaDTO ) {
        Page<Product> page = searchService.searchListPage( searchCreateriaDTO );
        ProductPageResponseDTO productPageResponseDTO = new ProductPageResponseDTO();

        productPageResponseDTO.setProducts( EntityToJAXB.parseListProductToProductsJAXB( page.getContent() ) );
        productPageResponseDTO.setFirst( page.isFirst() );
        productPageResponseDTO.setLast( page.isLast() );
        productPageResponseDTO.setNumber( page.getNumber() );
        productPageResponseDTO.setSize( page.getSize() );
        productPageResponseDTO.setTotalPages( page.getTotalPages() );
        productPageResponseDTO.setTotalElements( page.getTotalElements() );
        return productPageResponseDTO;
    }

    @PostMapping( value = "/danh-sach-san-pham.json",
            consumes = "application/json",
            produces = "application/json" )
    public Page<ProductsJAXB> searchListJSON( @RequestBody SearchCreateriaDTO searchCreateriaDTO ) {
        Page page = searchService.searchListPage( searchCreateriaDTO );
        return page;
    }
}
