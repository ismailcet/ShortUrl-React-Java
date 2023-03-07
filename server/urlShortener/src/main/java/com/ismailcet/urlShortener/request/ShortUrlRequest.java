package com.ismailcet.urlShortener.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlRequest {
    @NotNull
    @NotEmpty
    private String url;
    private String code;
}
