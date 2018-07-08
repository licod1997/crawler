package com.pickyourcpu.jaxb;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", namespace = "http://www.pickyourcpu.vn/schema/products", propOrder = {
        "product"
} )
@XmlRootElement( name = "products", namespace = "http://www.pickyourcpu.vn/schema/products" )
public class ProductsJAXB {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    private List<ProductJAXB> product;

    public List<ProductJAXB> getProduct() {
        if ( product == null ) {
            product = new ArrayList<>();
        }
        return product;
    }

    public void setProduct( List<ProductJAXB> product ) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductsJAXB{" +
                "product=" + product +
                '}';
    }
}