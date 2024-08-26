package com.demo.url_shortner.repository;


import com.demo.url_shortner.model.UrlMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatastoreRepository extends JpaRepository<UrlMapper, Long> {

    UrlMapper findByShortUrl(String shortUrl);
}
