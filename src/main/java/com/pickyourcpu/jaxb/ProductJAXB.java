package com.pickyourcpu.jaxb;

import javax.xml.bind.annotation.*;

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
        "description",
        "shops"
} )
@XmlRootElement( name = "product", namespace = "http://www.pickyourcpu.vn/schema/products" )
public class ProductJAXB {
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "long" )
    private Long id;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", required = true )
    private String name;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "integer" )
    private Integer benchmark;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    private String socket;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "double" )
    private Double clockspeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "double" )
    private Double turbospeed;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "double" )
    private Double TDP;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    @XmlSchemaType( name = "integer" )
    private Integer noOfCores;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    private String coresDescription;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true )
    private String description;
    @XmlElement( namespace = "http://www.pickyourcpu.vn/schema/products", nillable = true)
    private ShopsJAXB shops;

    public ProductJAXB() {
    }

    public ProductJAXB( Long id, String name, Integer benchmark, String socket, Double clockspeed, Double turbospeed, Double TDP, Integer noOfCores, String coresDescription, String description, ShopsJAXB shops ) {
        this.id = id;
        this.name = name;
        this.benchmark = benchmark;
        this.socket = socket;
        this.clockspeed = clockspeed;
        this.turbospeed = turbospeed;
        this.TDP = TDP;
        this.noOfCores = noOfCores;
        this.coresDescription = coresDescription;
        this.description = description;
        this.shops = shops;
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

    public ShopsJAXB getShops() {
        return shops;
    }

    public void setShops( ShopsJAXB shops ) {
        this.shops = shops;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !(o instanceof ProductJAXB) ) return false;

        ProductJAXB that = (ProductJAXB) o;

        if ( id != null ? !id.equals( that.id ) : that.id != null ) return false;
        if ( name != null ? !name.equals( that.name ) : that.name != null ) return false;
        if ( benchmark != null ? !benchmark.equals( that.benchmark ) : that.benchmark != null ) return false;
        if ( socket != null ? !socket.equals( that.socket ) : that.socket != null ) return false;
        if ( clockspeed != null ? !clockspeed.equals( that.clockspeed ) : that.clockspeed != null ) return false;
        if ( turbospeed != null ? !turbospeed.equals( that.turbospeed ) : that.turbospeed != null ) return false;
        if ( TDP != null ? !TDP.equals( that.TDP ) : that.TDP != null ) return false;
        if ( noOfCores != null ? !noOfCores.equals( that.noOfCores ) : that.noOfCores != null ) return false;
        if ( coresDescription != null ? !coresDescription.equals( that.coresDescription ) : that.coresDescription != null )
            return false;
        if ( description != null ? !description.equals( that.description ) : that.description != null ) return false;
        return shops != null ? shops.equals( that.shops ) : that.shops == null;
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
        result = 31 * result + (shops != null ? shops.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductJAXB{" +
                "id:" + id +
                ",\n name:'" + name + '\'' +
                ",\n benchmark:" + benchmark +
                ",\n socket:'" + socket + '\'' +
                ",\n clockspeed:" + clockspeed +
                ",\n turbospeed:" + turbospeed +
                ",\n TDP:" + TDP +
                ",\n noOfCores:" + noOfCores +
                ",\n coresDescription:'" + coresDescription + '\'' +
                ",\n description:'" + description + '\'' +
                ",\n shops:" + shops +
                '}';
    }
}
