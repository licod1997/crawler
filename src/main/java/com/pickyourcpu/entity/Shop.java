package com.pickyourcpu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Table
@Entity( name = "shop" )
public class Shop {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;
    @Column( name = "url", unique = true, nullable = false )
    private String url;
    @Column( name = "price", precision = 19, scale = 0 )
    private BigDecimal price;
    @JsonIgnore
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Product.class )
    @JoinColumn( name = "product_id", referencedColumnName = "id" )
    private Product product;

    public Shop() {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct( Product product ) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", product=" + product +
                '}';
    }
}
