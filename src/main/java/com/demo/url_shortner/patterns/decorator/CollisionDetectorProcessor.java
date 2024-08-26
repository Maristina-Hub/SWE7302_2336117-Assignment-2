package com.demo.url_shortner.patterns.decorator;


import com.demo.url_shortner.config.ApplicationContextProvider;
import com.demo.url_shortner.patterns.strategy.UrlMappingStrategy;
import com.demo.url_shortner.service.URLService;

import java.util.UUID;

public class CollisionDetectorProcessor implements UrlMappingStrategy {

    private UrlMappingStrategy strategy;

    public CollisionDetectorProcessor(UrlMappingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String createShortLink(String longUrl) {
        String shortUrl = strategy.createShortLink(longUrl);
        if (isCollision(shortUrl)) {
            return generateUniqueShortUrl(longUrl);
        }
        return shortUrl;
    }

    private boolean isCollision(String shortUrl) {
        URLService bean = ApplicationContextProvider.getApplicationContext().getBean(URLService.class);
        return bean.urlExists(shortUrl);
    }

    private String generateUniqueShortUrl(String longUrl) {
        return UUID.randomUUID().toString();
    }
}
