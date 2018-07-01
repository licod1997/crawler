package com.pickyourcpu.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "product", namespace = "http://www.pickyourcpu.vn/schema/products", propOrder = {
        "id",
        "name",
        "benchmark",
        "socket",
        "clockspeed",
        "turbospeed",
        "TDP",
        "noOfCores",
        "coresDescription",
        "description"
} )
@XmlRootElement( name = "product", namespace = "http://www.pickyourcpu.vn/schema/products" )
@Table
@Entity( name = "product" )
public class Product implements Serializable {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "long" )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    @Column( name = "name", unique = true, nullable = false )
    private String name;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "integer" )
    @Column( name = "benchmark" )
    private Integer benchmark;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @Column( name = "socket" )
    private String socket;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "double" )
    @Column( name = "clockspeed" )
    private Double clockspeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "double" )
    @Column( name = "turbospeed" )
    private Double turbospeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "double" )
    @Column( name = "TDP" )
    private Double TDP;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "integer" )
    @Column( name = "no_of_cores" )
    private Integer noOfCores;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @Column( name = "cores_description" )
    private String coresDescription;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
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

    public Integer getBenchmark() {
        return benchmark;
    }

    public void setBenchmark( Integer benchmark ) {
        this.benchmark = benchmark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Integer getNoOfCores() {
        return noOfCores;
    }

    public void setNoOfCores( Integer noOfCores ) {
        this.noOfCores = noOfCores;
    }

    public String getCoresDescription() {
        return coresDescription;
    }

    public void setCoresDescription( String coresDescription ) {
        this.coresDescription = coresDescription;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !(o instanceof Product) ) return false;

        Product product = (Product) o;

        if ( id != null ? !id.equals( product.id ) : product.id != null ) return false;
        if ( name != null ? !name.equals( product.name ) : product.name != null ) return false;
        if ( benchmark != null ? !benchmark.equals( product.benchmark ) : product.benchmark != null ) return false;
        if ( socket != null ? !socket.equals( product.socket ) : product.socket != null ) return false;
        if ( clockspeed != null ? !clockspeed.equals( product.clockspeed ) : product.clockspeed != null ) return false;
        if ( turbospeed != null ? !turbospeed.equals( product.turbospeed ) : product.turbospeed != null ) return false;
        if ( TDP != null ? !TDP.equals( product.TDP ) : product.TDP != null ) return false;
        if ( noOfCores != null ? !noOfCores.equals( product.noOfCores ) : product.noOfCores != null ) return false;
        if ( coresDescription != null ? !coresDescription.equals( product.coresDescription ) : product.coresDescription != null )
            return false;
        return description != null ? description.equals( product.description ) : product.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (benchmark != null ? benchmark.hashCode() : 0);
        result = 31 * result + (socket != null ? socket.hashCode() : 0);
        result = 31 * result + (clockspeed != null ? clockspeed.hashCode() : 0);
        result = 31 * result + (turbospeed != null ? turbospeed.hashCode() : 0);
        result = 31 * result + (TDP != null ? TDP.hashCode() : 0);
        result = 31 * result + (noOfCores != null ? noOfCores.hashCode() : 0);
        result = 31 * result + (coresDescription != null ? coresDescription.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
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
                ", noOfCores=" + noOfCores +
                ", coresDescription='" + coresDescription + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
