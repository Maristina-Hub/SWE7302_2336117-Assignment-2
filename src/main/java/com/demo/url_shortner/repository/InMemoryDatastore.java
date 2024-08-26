package com.demo.url_shortner.repository;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDatastore implements Datastore{
    private final Map<String, String> urlMap = new ConcurrentHashMap<>();

    @Override
    public void saveData(String shortUrl, String longUrl) {
        urlMap.put(shortUrl, longUrl);
    }

    @Override
    public String findData(String shortUrl) {

        return urlMap.get(shortUrl);
    }
}
