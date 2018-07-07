package com.pickyourcpu.dto;

import com.pickyourcpu.jaxb.ProductsJAXB;

import javax.xml.bind.annotation.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", namespace = "http://www.pickyourcpu.vn/schema/products", propOrder = {
        "first",
        "last",
        "totalPages",
        "totalElements",
        "size",
        "number",
        "products"
} )
@XmlRootElement( name = "response", namespace = "http://www.pickyourcpu.vn/schema/products" )
public class ProductPageResponseDTO {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    private ProductsJAXB products;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "boolean" )
    private boolean first;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "boolean" )
    private boolean last;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "int" )
    private int totalPages;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "long" )
    private long totalElements;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "int" )
    private int size;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "int" )
    private int number;

    public ProductPageResponseDTO() {
    }

    public ProductsJAXB getProducts() {
        return products;
    }

    public void setProducts( ProductsJAXB products ) {
        this.products = products;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst( boolean first ) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast( boolean last ) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages( int totalPages ) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements( long totalElements ) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize( int size ) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber( int number ) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ProductPageResponseDTO{" +
                "products=" + products +
                ", first=" + first +
                ", last=" + last +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", size=" + size +
                ", number=" + number +
                '}';
    }
}
