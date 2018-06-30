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
    public String crawlStart() {
        thread = new Thread( crawler );
        thread.start();
        return "crawl";
    }

    @GetMapping( value = "/dung-crawl" )
    public String crawlStop() {
        crawler.stopCrawling();
        try {
            thread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        return "crawl";
    }

    @GetMapping( value = "/kiem-tra-thread" )
    public ResponseEntity checkThread() {
        if ( thread != null && thread.isAlive() ) return ResponseEntity.ok( "Running" );
        return ResponseEntity.ok( "Stopped" );
    }
}
