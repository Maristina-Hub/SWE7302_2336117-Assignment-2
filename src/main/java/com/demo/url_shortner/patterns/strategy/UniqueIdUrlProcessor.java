package com.demo.url_shortner.patterns.strategy;


import com.demo.url_shortner.utils.UniqueIdGenerator;

public class UniqueIdUrlProcessor implements UrlMappingStrategy{

    private UniqueIdGenerator idGenerator = UniqueIdGenerator.getInstance();

    @Override
    public String createShortLink(String longUrl) {
        return idGenerator.generateUniqueId();
    }
}
