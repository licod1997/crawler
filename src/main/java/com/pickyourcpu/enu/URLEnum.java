package com.pickyourcpu.enu;

public enum URLEnum {

    CPUBENCHMARK_HOST( "https://www.cpubenchmark.net" ),
    CPUBENCHMARK_1( "https://www.cpubenchmark.net/high_end_cpus.html" ),
    CPUBENCHMARK_2( "https://www.cpubenchmark.net/mid_range_cpus.html" ),
    CPUBENCHMARK_3( "https://www.cpubenchmark.net/midlow_range_cpus.html" ),
    CPUBENCHMARK_4( "https://www.cpubenchmark.net/low_end_cpus.html" ),
    LONGBINH_HOST( "http://longbinh.com.vn" ),
    LONGBINH( "http://longbinh.com.vn/linh-kien-bo-vi-xu-ly-cpu" ),
    PHONGVU( "https://phongvu.vn/linh-kien-may-tinh/cpu-bo-vi-xu-ly.cat" );

    private final String url;

    URLEnum( String url ) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


}
