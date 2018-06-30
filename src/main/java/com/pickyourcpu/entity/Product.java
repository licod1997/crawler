package com.pickyourcpu.entity;

import javax.annotation.Generated;
import javax.persistence.*;
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
@Table
@Entity( name = "product" )
public class Product {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "long" )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @Column( name = "name" )
    private String name;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @XmlSchemaType( name = "positiveInteger" )
    @Column( name = "benchmark" )
    private BigInteger benchmark;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @Column( name = "socket" )
    private String socket;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @XmlSchemaType( name = "double" )
    @Column( name = "clockspeed" )
    private Double clockspeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @XmlSchemaType( name = "double" )
    @Column( name = "turbospeed" )
    private Double turbospeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @XmlSchemaType( name = "double" )
    @Column( name = "TDP" )
    private Double TDP;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @Column( name = "no_of_cores" )
    private String noOfCores;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products" )
    @Column( name = "description" )
    private String description;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
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

    public Double getTDP() {
        return TDP;
    }

    public void setTDP( Double TDP ) {
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
