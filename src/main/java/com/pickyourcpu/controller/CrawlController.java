package com.pickyourcpu.controller;

import com.pickyourcpu.crawler.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CrawlController {

    @Autowired
    private Crawler crawler;

    private Thread thread;

    @GetMapping( value = "/quan-tri-vien/crawl" )
    public ModelAndView getCrawlPage(ModelAndView mv) {
        mv.setViewName( "crawl" );
        return mv;
    }

    @GetMapping( value = "/quan-tri-vien/bat-dau-crawl" )
    public ResponseEntity crawlStart() {
        thread = new Thread( crawler );
        thread.start();
        return ResponseEntity.ok().build();
    }

    @GetMapping( value = "/quan-tri-vien/dung-crawl" )
    public ResponseEntity crawlStop() {
        crawler.stopCrawling();
        try {
            thread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping( value = "/quan-tri-vien/kiem-tra-thread" )
    public ResponseEntity checkThread() {
        if ( thread != null && thread.isAlive() ) {
            return ResponseEntity.ok( crawler.getResult() );
        }
        return ResponseEntity.notFound().build();
    }
}
