package com.pickyourcpu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping( value = "/dang-nhap" )
    public ModelAndView getLoginPage( ModelAndView mv ) {
        mv.setViewName( "dang-nhap" );
        return mv;
    }
}
