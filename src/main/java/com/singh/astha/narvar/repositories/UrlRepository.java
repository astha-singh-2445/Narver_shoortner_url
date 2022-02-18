package com.singh.astha.narvar.repositories;

import com.singh.astha.narvar.models.URLRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<URLRecord, String> {
    Optional<URLRecord> findByShortUrl(String shortUrl);
}
