package com.example.shobhit.url_shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerController {

    // 1. Inversion of control: Spring will create objects for you, you don't have to create
    // 2. Dependency Injection: Injecting the dependency (importing the objects already created by Spring) in your current class
    @Autowired
    UrlShortener urlShortener;

    @GetMapping("/expand_url")
    public String getLongUrl(@RequestParam("url") String shortUrl) throws Exception{
        return urlShortener.expandUrl(shortUrl);
    }
    @GetMapping("/shorten_url")
    public  String getShortUrl(@RequestParam("url") String longUrl){
        return urlShortener.shortenUrl(longUrl);
    }
}
