package com.demo.url_shortner.patterns.strategy;


import com.demo.url_shortner.utils.Base62UrlEncoder;

public class Base62UrlProcessor implements UrlMappingStrategy{

    @Override
    public String createShortLink(String longUrl) {
        long uniqueId = System.currentTimeMillis();
        return Base62UrlEncoder.encode(uniqueId);
    }
}
