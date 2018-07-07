package com.pickyourcpu.service;

import com.pickyourcpu.entity.Product;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;

public interface CompareService {
    List<Product> getComparomgList( Long id, HttpServletRequest req);
}
