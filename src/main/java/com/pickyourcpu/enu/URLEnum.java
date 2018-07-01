package com.pickyourcpu.enu;

public enum URLEnum {

    MAIN_WEBSITE_HOST( "https://www.cpubenchmark.net" ),
    MAIN_WEBSITE_1( "https://www.cpubenchmark.net/high_end_cpus.html" ),
    MAIN_WEBSITE_2( "https://www.cpubenchmark.net/mid_range_cpus.html" ),
    MAIN_WEBSITE_3( "https://www.cpubenchmark.net/midlow_range_cpus.html" ),
    MAIN_WEBSITE_4( "https://www.cpubenchmark.net/low_end_cpus.html" ),
    LB_WEBSITE( "http://longbinh.com.vn/linh-kien-bo-vi-xu-ly-cpu" );

    private final String url;

    URLEnum( String url ) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


}
