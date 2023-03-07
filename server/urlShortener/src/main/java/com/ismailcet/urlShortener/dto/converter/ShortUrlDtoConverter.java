package com.ismailcet.urlShortener.dto.converter;

import com.ismailcet.urlShortener.Entity.ShortUrl;
import com.ismailcet.urlShortener.dto.ShortUrlDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShortUrlDtoConverter {


    public ShortUrlDto convertToDto(ShortUrl from ){
        return ShortUrlDto.builder()
                .id(from.getId())
                .url(from.getUrl())
                .code(from.getCode())
                .build();
    }
    public List<ShortUrlDto> convertToDto(List<ShortUrl> from ){
        return from.stream()
                .map(x->convertToDto(x))
                .collect(Collectors.toList());
    }
}
