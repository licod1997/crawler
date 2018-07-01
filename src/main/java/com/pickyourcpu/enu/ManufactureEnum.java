package com.pickyourcpu.enu;

public enum ManufactureEnum {
    INTEL("Intel"),
    AMD("AMD");

    private String manufacture;

    ManufactureEnum( String manufacture ) {
        this.manufacture = manufacture;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture( String manufacture ) {
        this.manufacture = manufacture;
    }
}
