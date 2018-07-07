package com.pickyourcpu.controller;

import com.pickyourcpu.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DetailController {
    @Autowired
    private SearchService searchService;

    @GetMapping( value = "chi-tiet-san-pham" )
    public ModelAndView getProductDetail( @RequestParam( value = "id", defaultValue = "0" ) Long id,
                                          ModelAndView mv ) {
        mv.setViewName( "chi-tiet-san-pham" );
        mv.addObject( "product", searchService.getProductDetail( id ) );
        return mv;
    }
}
