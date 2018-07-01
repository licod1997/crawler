package com.pickyourcpu.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchCreateriaDTO {
    private String manufacture;
    private String socket;
    private String type;
    private Integer noOfCores;

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
}
