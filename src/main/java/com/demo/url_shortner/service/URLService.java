package com.demo.url_shortner.service;


import com.demo.url_shortner.patterns.decorator.CollisionDetectorProcessor;
import com.demo.url_shortner.patterns.factory.DatastoreFactory;
import com.demo.url_shortner.patterns.strategy.Base62UrlProcessor;
import com.demo.url_shortner.patterns.strategy.MultiStrategyUrlProcessor;
import com.demo.url_shortner.patterns.strategy.UniqueIdUrlProcessor;
import com.demo.url_shortner.patterns.strategy.UrlMappingStrategy;
import com.demo.url_shortner.repository.Datastore;
import com.demo.url_shortner.utils.ValidationUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class URLService {

    @Value("${database.type}")
    private String databaseType;

    @Value("${base.url}")
    private String baseUrl;

    private UrlMappingStrategy urlGenerationStrategy;
    private Datastore datastore;

    @PostConstruct
    public void init() {
        if (databaseType == null || databaseType.isEmpty()) {
            throw new IllegalArgumentException("Database type is not found.");
        }

        // Factory to get appropriate database instance
        this.datastore = DatastoreFactory.createDatastore(databaseType);

        // Composite Strategy with collision detection
        UrlMappingStrategy base62Strategy = new Base62UrlProcessor();
        UrlMappingStrategy uniqueIdStrategy = new UniqueIdUrlProcessor();
        UrlMappingStrategy collisionDetectingStrategy = new CollisionDetectorProcessor(
                new MultiStrategyUrlProcessor(Arrays.asList(base62Strategy, uniqueIdStrategy)));

        this.urlGenerationStrategy = collisionDetectingStrategy;
    }

    public String shortenUrl(String longUrl) {
        // Validate and sanitize the long URL
        if (!ValidationUtils.isURLValid(longUrl)) {
            throw new IllegalArgumentException("Invalid URL format.");
        }

        String sanitizedUrl = ValidationUtils.sanitize(longUrl);
        String shortCode = urlGenerationStrategy.createShortLink(sanitizedUrl);
        String shortUrl = baseUrl + shortCode;
        datastore.saveData(shortCode, sanitizedUrl);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl) {
        return datastore.findData(shortUrl);
    }

    public boolean urlExists(String shortUrl) {
        return datastore.findData(shortUrl) != null;
    }
}
