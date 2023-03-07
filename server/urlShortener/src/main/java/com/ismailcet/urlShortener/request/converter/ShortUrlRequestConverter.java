package com.ismailcet.urlShortener.request.converter;

import com.ismailcet.urlShortener.Entity.ShortUrl;
import com.ismailcet.urlShortener.request.ShortUrlRequest;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlRequestConverter {

    public ShortUrl convertToEntity(ShortUrlRequest from){
        return ShortUrl.builder()
                .url(from.getUrl())
                .code(from.getCode())
                .build();
    }
}
