package com.pickyourcpu.jaxb;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "shop", namespace = "http://www.pickyourcpu.vn/schema/products", propOrder = {
        "id",
        "url",
        "price"
} )
@XmlRootElement( name = "shop", namespace = "http://www.pickyourcpu.vn/schema/products" )
public class ShopJAXB {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "long" )
    private Long id;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    private String url;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "positiveInteger" )
    private BigDecimal price;

    public ShopJAXB() {
    }

    public ShopJAXB( Long id, String url, BigDecimal price ) {
        this.id = id;
        this.url = url;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !(o instanceof ShopJAXB) ) return false;

        ShopJAXB shopJAXB = (ShopJAXB) o;

        if ( id != null ? !id.equals( shopJAXB.id ) : shopJAXB.id != null ) return false;
        if ( url != null ? !url.equals( shopJAXB.url ) : shopJAXB.url != null ) return false;
        return price != null ? price.equals( shopJAXB.price ) : shopJAXB.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShopJAXB{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", price=" + price +
                '}';
    }
}