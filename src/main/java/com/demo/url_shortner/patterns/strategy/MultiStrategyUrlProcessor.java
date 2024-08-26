package com.demo.url_shortner.patterns.strategy;


import com.demo.url_shortner.config.ApplicationContextProvider;
import com.demo.url_shortner.service.URLService;

import java.util.List;

public class MultiStrategyUrlProcessor implements UrlMappingStrategy{
    private List<UrlMappingStrategy> strategies;

    public MultiStrategyUrlProcessor(List<UrlMappingStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public String createShortLink(String longUrl) {
        for (UrlMappingStrategy strategy : strategies) {
            String shortUrl = strategy.createShortLink(longUrl);
            if (!isCollision(shortUrl)) {
                return shortUrl;
            }
        }
        throw new RuntimeException("Unable to generate a unique short URL");
    }

    private boolean isCollision(String shortUrl) {
        URLService bean = ApplicationContextProvider.getApplicationContext().getBean(URLService.class);
        return bean.urlExists(shortUrl);
    }
}
