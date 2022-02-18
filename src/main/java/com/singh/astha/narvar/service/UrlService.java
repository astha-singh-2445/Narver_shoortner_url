package com.singh.astha.narvar.service;

import com.singh.astha.narvar.dtos.request.UrlRedirectRequest;
import com.singh.astha.narvar.dtos.request.UrlShortenRequest;
import com.singh.astha.narvar.dtos.response.UrlResponse;

public interface UrlService {
    UrlResponse shortenUrl(UrlShortenRequest urlShortenRequest);

    UrlResponse fetchRedirectUrl(UrlRedirectRequest urlRedirectRequest);
}
