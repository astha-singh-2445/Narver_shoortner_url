package com.singh.astha.narvar.service.impl;

import com.singh.astha.narvar.dtos.request.UrlRedirectRequest;
import com.singh.astha.narvar.dtos.request.UrlShortenRequest;
import com.singh.astha.narvar.dtos.response.UrlResponse;
import com.singh.astha.narvar.exception.ResponseException;
import com.singh.astha.narvar.models.Counter;
import com.singh.astha.narvar.models.URLRecord;
import com.singh.astha.narvar.repositories.CounterRepository;
import com.singh.astha.narvar.repositories.UrlRepository;
import com.singh.astha.narvar.service.UrlService;
import com.singh.astha.narvar.utils.Constants;
import com.singh.astha.narvar.utils.StaticMethods;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final CounterRepository counterRepository;
    private final MongoTemplate mongoTemplate;

    public UrlServiceImpl(UrlRepository urlRepository, CounterRepository counterRepository, MongoTemplate mongoTemplate) {
        this.urlRepository = urlRepository;
        this.counterRepository = counterRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public UrlResponse shortenUrl(UrlShortenRequest urlShortenRequest) {
        long counter = incrementCounter();
        String shortUrl = StaticMethods.shortenUrl(counter);
        URLRecord urlRecord = URLRecord.builder()
                .longUrl(urlShortenRequest.getUrl())
                .shortUrl(shortUrl)
                .build();
        urlRecord = urlRepository.save(urlRecord);
        return UrlResponse.builder()
                .longUrl(urlRecord.getLongUrl())
                .shortKey(urlRecord.getShortUrl())
                .build();
    }

    @Override
    public UrlResponse fetchRedirectUrl(UrlRedirectRequest urlRedirectRequest) {
        Optional<URLRecord> urlRecordOptional = urlRepository.findByShortUrl(urlRedirectRequest.getShortKey());
        if (urlRecordOptional.isEmpty()) {
            throw new ResponseException(HttpStatus.BAD_REQUEST, Constants.SHORT_KEY_NOT_FOUND);
        }
        URLRecord urlRecord = urlRecordOptional.get();
        return UrlResponse.builder()
                .longUrl(urlRecord.getLongUrl())
                .shortKey(urlRecord.getShortUrl())
                .build();
    }

    private long incrementCounter() {
        Query query = new Query().addCriteria(Criteria.where(Constants.ID).is(Constants.URL_COUNTER));
        Update update = new Update().inc(Constants.SEQUENCE_VALUE, 1);
        Counter newCounter = mongoTemplate.findAndModify(query, update, Counter.class);
        assert newCounter != null;
        return newCounter.getSequenceValue();
    }

}
