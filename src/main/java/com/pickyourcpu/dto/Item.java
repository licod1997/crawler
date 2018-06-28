package com.pickyourcpu.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {
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
