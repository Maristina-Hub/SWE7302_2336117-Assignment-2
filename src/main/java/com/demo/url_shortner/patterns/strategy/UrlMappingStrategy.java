package com.demo.url_shortner.patterns.strategy;


public interface UrlMappingStrategy {
    String createShortLink(String longUrl);
}
