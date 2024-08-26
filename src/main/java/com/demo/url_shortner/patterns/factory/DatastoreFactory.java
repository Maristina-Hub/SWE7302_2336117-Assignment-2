package com.demo.url_shortner.patterns.factory;


import com.demo.url_shortner.repository.Datastore;
import com.demo.url_shortner.repository.DatastoreRepository;
import com.demo.url_shortner.repository.InMemoryDatastore;
import com.demo.url_shortner.repository.PersistentDatastore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DatastoreFactory {

    public static Datastore createDatastore(String type) {
        if ("in-memory".equalsIgnoreCase(type)) {
            return new InMemoryDatastore();
        } else if ("persistent".equalsIgnoreCase(type)) {
            return new PersistentDatastore();
        } else {
            throw new IllegalArgumentException("Unknown datastore type: " + type);
        }
    }
}
