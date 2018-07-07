package com.pickyourcpu.controller;

import com.pickyourcpu.entity.Product;
import com.pickyourcpu.service.CompareService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CompareController {

    @Autowired
    private CompareService compareService;

    @GetMapping( "/so-sanh-san-pham" )
    public ModelAndView compareProduct( HttpServletRequest req,
                                        ModelAndView mv ) {
        mv.setViewName( "so-sanh-san-pham" );
        mv.addObject( "list", req.getSession().getAttribute( "COMPARE" ) );
        return mv;
    }

    @GetMapping( "/them-san-pham-vao-so-sanh" )
    public ResponseEntity addCompareProduct( @RequestParam( value = "id", defaultValue = "0" ) Long id,
                                             HttpServletRequest req ) {
        List<Product> list = compareService.getComparomgList( id, req );
        return ResponseEntity.ok( list );
    }
}
