package com.singh.astha.narvar.dtos.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UrlResponse {
    private String longUrl;
    private String shortKey;
}
