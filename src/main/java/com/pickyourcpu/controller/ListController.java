package com.pickyourcpu.controller;

import com.pickyourcpu.dto.ProductPageResponse;
import com.pickyourcpu.dto.SearchCreateriaDTO;
import com.pickyourcpu.entity.Product;
import com.pickyourcpu.entity.Shop;
import com.pickyourcpu.jaxb.ProductJAXB;
import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.jaxb.ShopJAXB;
import com.pickyourcpu.jaxb.ShopsJAXB;
import com.pickyourcpu.service.SearchListService;
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
    private SearchListService searchListService;

    @GetMapping( value = "/danh-sach-san-pham" )
    public ModelAndView getListPage( ModelAndView mv ) {
        mv.setViewName( "danh-sach-san-pham" );
        SearchCreateriaDTO searchCreateriaDTO = new SearchCreateriaDTO();
        searchCreateriaDTO.setPage( 1 );
        searchCreateriaDTO.setMaxSize( 20 );
        searchCreateriaDTO.setSort( "DESC" );
        searchCreateriaDTO.setField( "benchmark" );
        mv.addObject( "page", searchListService.searchListPage( searchCreateriaDTO ) );
        mv.addObject( "socketList", searchListService.getSocketList() );
        mv.addObject( "noOfCoresList", searchListService.getNoOfCoresList() );
        return mv;
    }

    @PostMapping( value = "/danh-sach-san-pham.xml",
            consumes = "application/json",
            produces = "application/xml" )
    public ProductPageResponse searchListXML( @RequestBody SearchCreateriaDTO searchCreateriaDTO ) {
        Page<Product> page = searchListService.searchListPage( searchCreateriaDTO );

        ProductPageResponse productPageResponse = new ProductPageResponse();
        ProductsJAXB productsJAXB = new ProductsJAXB();

        for ( Product product : page.getContent() ) {
            ShopsJAXB shopsJAXB = new ShopsJAXB();

            for ( Shop shop : product.getShops() ) {
                shopsJAXB.getShop().add( new ShopJAXB(
                        shop.getId(),
                        shop.getUrl(),
                        shop.getPrice()
                ) );
            }

            ProductJAXB productJAXB = new ProductJAXB(
                    product.getId(),
                    product.getName(),
                    product.getBenchmark(),
                    product.getSocket(),
                    product.getClockspeed(),
                    product.getTurbospeed(),
                    product.getTDP(),
                    product.getNoOfCores(),
                    product.getCoresDescription(),
                    product.getDescription(),
                    shopsJAXB
            );
            productsJAXB.getProduct().add( productJAXB );
        }

        productPageResponse.setProducts( productsJAXB );
        productPageResponse.setFirst( page.isFirst() );
        productPageResponse.setLast( page.isLast() );
        productPageResponse.setNumber( page.getNumber() );
        productPageResponse.setSize( page.getSize() );
        productPageResponse.setTotalPages( page.getTotalPages() );
        productPageResponse.setTotalElements( page.getTotalElements() );
        return productPageResponse;
    }

    @PostMapping( value = "/danh-sach-san-pham.json",
            consumes = "application/json",
            produces = "application/json" )
    public Page<ProductsJAXB> searchListJSON( @RequestBody SearchCreateriaDTO searchCreateriaDTO ) {
        Page page = searchListService.searchListPage( searchCreateriaDTO );
        return page;
    }
}
