package com.pickyourcpu.controller;

import com.pickyourcpu.crawler.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrawlController {

    @Autowired
    private Crawler crawler;

    private Thread thread;

    @GetMapping( value = "/bat-dau-crawl" )
    public ResponseEntity crawlStart() {
        if (thread == null) {
            thread = new Thread( crawler );
        }
        thread.start();
        return ResponseEntity.ok( "Crawler is starting" );
    }

}
