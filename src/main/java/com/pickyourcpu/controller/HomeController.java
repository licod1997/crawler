package com.pickyourcpu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping( value = "/trang-chu" )
    public ModelAndView homepage( ModelAndView mv ) {
        mv.setViewName( "trang-chu" );
        mv.addObject( "mess", "Hello to my app!!!" );
        return mv;
    }
}
