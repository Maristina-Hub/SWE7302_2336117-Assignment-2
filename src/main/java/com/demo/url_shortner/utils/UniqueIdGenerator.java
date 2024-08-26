package com.demo.url_shortner.utils;


import java.util.UUID;

public class UniqueIdGenerator {
    private static UniqueIdGenerator instance;

    private UniqueIdGenerator(){
        if (instance != null)
            throw new RuntimeException("Instance has already been initialized!");
    }

    public static UniqueIdGenerator getInstance(){
        if(instance == null){
            instance = new UniqueIdGenerator();
        }
        return instance;
    }

    public String generateUniqueId() {

        return UUID.randomUUID().toString();
    }
}
