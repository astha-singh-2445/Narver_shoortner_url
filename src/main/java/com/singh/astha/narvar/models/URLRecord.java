package com.singh.astha.narvar.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("url")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class URLRecord {
    @Id
    private String id;
    @Indexed(unique = true)
    private String shortUrl;
    private String longUrl;
}
