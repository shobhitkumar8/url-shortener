package com.example.shobhit.url_shortener;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class UrlShortener {

    private String domain; //abc.com
    private int lengthOfShortUrl;
    private Map<String, String> longToShortUrlMap; // long url to short mapping
    private Map<String, String> shortToLongUrlMap; // short to long url mapping
    private ArrayList<Character> allowedChars;

    // a-z, A-Z, 0-9
    // https://www.flipkart.com/shopping-cart/2343/bill

    UrlShortener(){
        this("abc.com", 5);

    }
    UrlShortener(String domain, int length) {
        this.domain = domain;
        this.lengthOfShortUrl = length;

        this.longToShortUrlMap = new HashMap<>();
        this.shortToLongUrlMap = new HashMap<>();

        this.allowedChars = new ArrayList<>();

        for (char i = 'a'; i <= 'z'; i++) {
            this.allowedChars.add(i);
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            this.allowedChars.add(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            this.allowedChars.add(i);
        }
    }

    public String shortenUrl(String longUrl) { // returning short url

        if (longUrl.length() <= this.lengthOfShortUrl) {
            return longUrl;
        }
        if (longToShortUrlMap.containsKey(longUrl)) {
            return longToShortUrlMap.get(longUrl);
        }

        String shortUrl = this.domain + "/";
        while (true) {
            for (int i = 0; i < this.lengthOfShortUrl; i++) {
                Random random = new Random();
                int index = random.nextInt(62); // 0 - 61
                char ch = this.allowedChars.get(index);
                shortUrl += ch;
            }
//            System.out.println("short url is " + shortUrl);

            if (!shortToLongUrlMap.containsKey(shortUrl)) {
                // Here it means that we generated a unique short url
                break;
            }
            // It means that short url which is generated already exists, so we need to generate a new one
        }
        shortToLongUrlMap.put(shortUrl, longUrl); // given a long url, you need to short url
        longToShortUrlMap.put(longUrl, shortUrl); // given a short url, you need to expand url

        return shortUrl;
    }

    public String expandUrl(String shortUrl) throws Exception { // returning long url

        if (shortToLongUrlMap.containsKey(shortUrl)) {
            return shortToLongUrlMap.get((shortUrl));
        }
        throw new Exception("No short url found");
    }
}
