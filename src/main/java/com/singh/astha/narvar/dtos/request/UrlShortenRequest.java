package com.singh.astha.narvar.dtos.request;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UrlShortenRequest {
    @NotBlank
    @URL
    private String url;
}
