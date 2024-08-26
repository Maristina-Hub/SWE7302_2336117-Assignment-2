package com.demo.url_shortner.repository;


public interface Datastore {

    void saveData (String shortUrl, String longUrl);
    String findData (String shortUrl);

}
