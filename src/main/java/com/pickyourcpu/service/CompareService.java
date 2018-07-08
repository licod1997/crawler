package com.pickyourcpu.service;

import com.pickyourcpu.entity.Product;
import com.pickyourcpu.jaxb.ProductsJAXB;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;

public interface CompareService {
    ProductsJAXB addComparingList( Long id, HttpServletRequest req);
    ProductsJAXB removeComparingList( Long id, HttpServletRequest req);
}
