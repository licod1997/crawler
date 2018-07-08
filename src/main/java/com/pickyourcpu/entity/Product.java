package com.pickyourcpu.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity( name = "product" )
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;
    @Column( name = "name", unique = true, nullable = false )
    private String name;
    @Column( name = "benchmark" )
    private Integer benchmark;
    @Column( name = "socket" )
    private String socket;
    @Column( name = "clockspeed" )
    private Double clockspeed;
    @Column( name = "turbospeed" )
    private Double turbospeed;
    @Column( name = "TDP" )
    private Double TDP;
    @Column( name = "no_of_cores" )
    private Integer noOfCores;
    @Column( name = "cores_description" )
    private String coresDescription;
    @Column( name = "description" )
    private String description;
    @OneToMany( mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, targetEntity = Shop.class )
    private List<Shop> shops;

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

    public List<Shop> getShops() {
        if ( shops == null ) {
            shops = new ArrayList<>();
        }
        return shops;
    }

    public void setShops( List<Shop> shops ) {
        this.shops = shops;
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
                ",\n name='" + name + '\'' +
                ",\n benchmark=" + benchmark +
                ",\n socket='" + socket + '\'' +
                ",\n clockspeed=" + clockspeed +
                ",\n turbospeed=" + turbospeed +
                ",\n TDP=" + TDP +
                ",\n noOfCores=" + noOfCores +
                ",\n coresDescription='" + coresDescription + '\'' +
                ",\n description='" + description + '\'' +
                ",\n shops=" + shops +
                '}';
    }
}
