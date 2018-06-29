package com.pickyourcpu.entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "product", namespace = "http://www.pickyourcpu.vn/schema/products", propOrder = {
        "id",
        "name",
        "socket",
        "clockspeed",
        "TDP"
} )
@XmlRootElement( name = "product", namespace = "http://www.pickyourcpu.vn/schema/products" )
public class Product {

    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "positiveInteger" )
    private BigInteger id;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    private String name;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "positiveInteger" )
    private BigInteger benchmark;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    private String socket;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @XmlSchemaType( name = "double" )
    private Double clockspeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @XmlSchemaType( name = "double" )
    private Double turbospeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @XmlSchemaType( name = "positiveInteger" )
    private BigInteger TDP;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    private String noOfCores;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    private String description;

    public Product() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId( BigInteger id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket( String socket ) {
        this.socket = socket;
    }

    public Double getClockspeed() {
        return clockspeed;
    }

    public void setClockspeed( Double clockspeed ) {
        this.clockspeed = clockspeed;
    }

    public BigInteger getTDP() {
        return TDP;
    }

    public void setTDP( BigInteger TDP ) {
        this.TDP = TDP;
    }

    public Double getTurbospeed() {
        return turbospeed;
    }

    public void setTurbospeed( Double turbospeed ) {
        this.turbospeed = turbospeed;
    }

    public BigInteger getBenchmark() {
        return benchmark;
    }

    public void setBenchmark( BigInteger benchmark ) {
        this.benchmark = benchmark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getNoOfCores() {
        return noOfCores;
    }

    public void setNoOfCores( String noOfCores ) {
        this.noOfCores = noOfCores;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", benchmark=" + benchmark +
                ", socket='" + socket + '\'' +
                ", clockspeed=" + clockspeed +
                ", turbospeed=" + turbospeed +
                ", TDP=" + TDP +
                ", noOfCores='" + noOfCores + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
