package com.pickyourcpu.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", namespace = "http://www.pickyourcpu.vn/schema/products", propOrder = {
        "product"
} )
@XmlRootElement( name = "products", namespace = "http://www.pickyourcpu.vn/schema/products" )
public class Products {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    protected List<Product> product;

    public List<Product> getProduct() {
        if ( product == null ) {
            product = new ArrayList<>();
        }
        return this.product;
    }
}
