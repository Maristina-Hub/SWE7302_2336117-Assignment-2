package com.demo.url_shortner.utils;


import java.net.URL;

public class ValidationUtils {

    public static boolean isURLValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String sanitize(String url) {
        return url.trim();
    }
}
