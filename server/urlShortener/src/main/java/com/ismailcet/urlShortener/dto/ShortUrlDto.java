package com.ismailcet.urlShortener.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlDto {

    private Long id;
    private String url;
    private String code;
}
