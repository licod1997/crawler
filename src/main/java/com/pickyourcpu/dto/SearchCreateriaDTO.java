package com.pickyourcpu.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class SearchCreateriaDTO {
    private ArrayList<String> manufacture;
    private ArrayList<String> socket;
    private ArrayList<String> type;
    private ArrayList<Integer> noOfCores;
    private Integer page;
    private Integer maxSize;
    private Integer totalPage;
    private String sort;
    private String field;
    private Boolean buyable;
    private ArrayList<String> price;

    public SearchCreateriaDTO() {
    }

    public ArrayList<String> getManufacture() {
        if ( manufacture == null ) {
            manufacture = new ArrayList<>();
        }
        return manufacture;
    }

    public void setManufacture( ArrayList<String> manufacture ) {
        this.manufacture = manufacture;
    }

    public ArrayList<String> getSocket() {
        if ( socket == null ) {
            socket = new ArrayList<>();
        }
        return socket;
    }

    public void setSocket( ArrayList<String> socket ) {
        this.socket = socket;
    }

    public ArrayList<String> getType() {
        if ( type == null ) {
            type = new ArrayList<>();
        }
        return type;
    }

    public void setType( ArrayList<String> type ) {
        this.type = type;
    }

    public ArrayList<Integer> getNoOfCores() {
        if ( noOfCores == null ) {
            noOfCores = new ArrayList<>();
        }
        return noOfCores;
    }

    public void setNoOfCores( ArrayList<Integer> noOfCores ) {
        this.noOfCores = noOfCores;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage( Integer page ) {
        this.page = page;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize( Integer maxSize ) {
        this.maxSize = maxSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage( Integer totalPage ) {
        this.totalPage = totalPage;
    }

    public String getSort() {
        return sort;
    }

    public void setSort( String sort ) {
        this.sort = sort;
    }

    public String getField() {
        return field;
    }

    public void setField( String field ) {
        this.field = field;
    }

    public Boolean getBuyable() {
        if ( buyable == null ) {
            buyable = true;
        }
        return buyable;
    }

    public void setBuyable( Boolean buyable ) {
        this.buyable = buyable;
    }

    public ArrayList<String> getPrice() {
        if ( price == null ) {
            price = new ArrayList<>();
        }
        return price;
    }

    public void setPrice( ArrayList<String> price ) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SearchCreateriaDTO{" +
                "manufacture=" + manufacture +
                ", socket=" + socket +
                ", type=" + type +
                ", noOfCores=" + noOfCores +
                ", page=" + page +
                ", maxSize=" + maxSize +
                ", totalPage=" + totalPage +
                ", sort='" + sort + '\'' +
                ", field='" + field + '\'' +
                ", buyable=" + buyable +
                ", price=" + price +
                '}';
    }
}
