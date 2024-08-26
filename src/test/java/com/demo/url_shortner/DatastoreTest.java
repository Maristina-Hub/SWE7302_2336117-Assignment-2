package com.demo.url_shortner;


import com.demo.url_shortner.repository.InMemoryDatastore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatastoreTest {
    private InMemoryDatastore database;

    @BeforeEach
    void setUp() {
        database = new InMemoryDatastore();
    }

    @Test
    void testStoreAndFindShortUrl() {
        String longUrl = "http://example-code.com";
        String shortUrl = "abs154";

        database.saveData(shortUrl, longUrl);
        String foundShortUrl = database.findData(shortUrl);
        assertEquals(longUrl, foundShortUrl, "The found long URL should match the stored one");
    }
}
