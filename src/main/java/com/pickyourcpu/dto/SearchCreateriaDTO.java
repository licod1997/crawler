package com.pickyourcpu.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchCreateriaDTO {
    private String manufacture;
    private String socket;
    private String type;
    private Integer noOfCores;
    private Integer page;
    private Integer maxSize;
    private Integer totalPage;
    private String sort;
    private String field;

    public SearchCreateriaDTO() {
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture( String manufacture ) {
        this.manufacture = manufacture;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket( String socket ) {
        this.socket = socket;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public Integer getNoOfCores() {
        return noOfCores;
    }

    public void setNoOfCores( Integer noOfCores ) {
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
}
