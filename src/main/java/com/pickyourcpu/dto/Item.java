package com.pickyourcpu.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "item", namespace = "http://something.com/schema/items", propOrder = {
        "name"
} )
@XmlRootElement( name = "item", namespace = "http://something.com/schema/items" )
public class Item {

    @XmlElement( name = "name", namespace = "http://something.com/schema/items" )
    private String name;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
