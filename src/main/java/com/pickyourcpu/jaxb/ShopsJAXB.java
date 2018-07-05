package com.pickyourcpu.jaxb;

import com.pickyourcpu.entity.Shop;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", namespace = "http://www.pickyourcpu.vn/schema/products", propOrder = {
        "shop"
} )
@XmlRootElement( name = "shops", namespace = "http://www.pickyourcpu.vn/schema/products" )
public class ShopsJAXB {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    private List<ShopJAXB> shop;

    public List<ShopJAXB> getShop() {
        if ( shop == null ) {
            shop = new ArrayList<>();
        }
        return shop;
    }

    public void setShop( List<ShopJAXB> shop ) {
        this.shop = shop;
    }
}
