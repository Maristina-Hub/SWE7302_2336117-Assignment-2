package com.demo.url_shortner.repository;


import com.demo.url_shortner.model.UrlMapper;
import com.demo.url_shortner.patterns.factory.DatastoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.demo.url_shortner.constants.AppConstants.SYSTEM_USER;

@Component
public class PersistentDatastore implements Datastore {

    @Autowired
    private DatastoreRepository datastoreRepository;

//    @Autowired
//    public PersistentDatastore(DatastoreRepository datastoreRepository) {
//        this.datastoreRepository = datastoreRepository;
//    }

    @Override
    public void saveData(String shortUrl, String longUrl) {
        UrlMapper mapper = new UrlMapper();
        mapper.setShortUrl(shortUrl);
        mapper.setLongUrl(longUrl);
        mapper.setCreatedAt(LocalDateTime.now());
        mapper.setUpdatedAt(LocalDateTime.now());
        mapper.setCreatedBy(SYSTEM_USER);
        mapper.setUpdatedBy(SYSTEM_USER);
        datastoreRepository.save(mapper);
    }

    @Override
    public String findData(String shortUrl) {
        UrlMapper mapping = datastoreRepository.findByShortUrl(shortUrl);
        return mapping != null ? mapping.getLongUrl() : null;
    }
}
