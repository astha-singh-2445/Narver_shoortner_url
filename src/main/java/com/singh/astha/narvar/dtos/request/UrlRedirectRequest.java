package com.singh.astha.narvar.dtos.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UrlRedirectRequest {
    @NotBlank
    private String shortKey;
}
