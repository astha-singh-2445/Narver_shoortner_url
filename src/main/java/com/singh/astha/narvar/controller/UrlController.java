package com.singh.astha.narvar.controller;

import com.singh.astha.narvar.dtos.request.UrlRedirectRequest;
import com.singh.astha.narvar.dtos.request.UrlShortenRequest;
import com.singh.astha.narvar.dtos.response.ResponseWrapper;
import com.singh.astha.narvar.dtos.response.UrlResponse;
import com.singh.astha.narvar.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/url")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ResponseWrapper<UrlResponse>> shortenUrl(@Valid @RequestBody UrlShortenRequest urlShortenRequest) {
        UrlResponse urlResponse = urlService.shortenUrl(urlShortenRequest);
        return ResponseEntity.ok(ResponseWrapper.success(urlResponse));
    }

    @PostMapping("/redirect")
    public ResponseEntity<ResponseWrapper<UrlResponse>> fetchRedirectUrl(@Valid @RequestBody UrlRedirectRequest urlRedirectRequest) {
        UrlResponse urlResponse = urlService.fetchRedirectUrl(urlRedirectRequest);
        return ResponseEntity.ok(ResponseWrapper.success(urlResponse));
    }
}
