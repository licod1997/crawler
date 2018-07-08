package com.pickyourcpu.controller;

import com.pickyourcpu.jaxb.ProductsJAXB;
import com.pickyourcpu.service.CompareService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CompareController {

    @Autowired
    private CompareService compareService;

    @GetMapping( value = "/so-sanh-san-pham" )
    public ModelAndView compareProduct( ModelAndView mv ) {
        mv.setViewName( "so-sanh-san-pham" );
        return mv;
    }

    @GetMapping( value = "/so-sanh-san-pham.xml",
            produces = "application/xml" )
    public ProductsJAXB getCompareProductXML( HttpServletRequest req ) {
        return (ProductsJAXB) req.getSession().getAttribute( "COMPARE" );
    }

    @GetMapping( value = "/them-san-pham-vao-so-sanh.xml",
            produces = "application/xml" )
    public ProductsJAXB addCompareProduct( @RequestParam( value = "id", defaultValue = "0" ) Long id,
                                           HttpServletRequest req ) {
        return compareService.addComparingList( id, req );
    }

    @GetMapping( value = "/xoa-san-pham-khoi-so-sanh.xml",
            produces = "application/xml" )
    public ProductsJAXB removeCompareProduct( @RequestParam( value = "id", defaultValue = "0" ) Long id,
                                              HttpServletRequest req ) {
        return compareService.removeComparingList( id, req );
    }
}
