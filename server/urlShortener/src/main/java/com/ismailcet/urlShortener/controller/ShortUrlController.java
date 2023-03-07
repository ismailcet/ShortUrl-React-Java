package com.ismailcet.urlShortener.controller;

import com.ismailcet.urlShortener.Entity.ShortUrl;
import com.ismailcet.urlShortener.dto.ShortUrlDto;
import com.ismailcet.urlShortener.dto.converter.ShortUrlDtoConverter;
import com.ismailcet.urlShortener.request.ShortUrlRequest;
import com.ismailcet.urlShortener.request.converter.ShortUrlRequestConverter;
import com.ismailcet.urlShortener.service.ShortUrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping()
public class ShortUrlController {

    private final ShortUrlDtoConverter shortUrlDtoConverter;
    private final ShortUrlRequestConverter shortUrlRequestConverter;
    private final ShortUrlService service;


    public ShortUrlController(ShortUrlDtoConverter converter, ShortUrlRequestConverter shortUrlRequestConverter, ShortUrlService service) {
        this.shortUrlDtoConverter = converter;
        this.shortUrlRequestConverter = shortUrlRequestConverter;
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ShortUrlDto>> getAllUrls(){
        return new ResponseEntity<List<ShortUrlDto>>(
                shortUrlDtoConverter.convertToDto(service.getAllShortUrl()), HttpStatus.OK
        );
    }

    @GetMapping("/show/{code}")
    public ResponseEntity<ShortUrlDto> getUrlByCode(@Valid @NotEmpty @PathVariable String code){
        return new ResponseEntity<ShortUrlDto>(
                shortUrlDtoConverter.convertToDto(service.getUrlByCode(code)), HttpStatus.OK
        );
    }

    @GetMapping("/{code}")
    public ResponseEntity<ShortUrlDto> redirect(@Valid @NotEmpty @PathVariable String code) throws URISyntaxException {
        ShortUrl shortUrl = service.getUrlByCode(code);

        URI uri = new URI(shortUrl.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(
                httpHeaders , HttpStatus.SEE_OTHER
        );
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody ShortUrlRequest shortUrlRequest){

        ShortUrl shortUrl = shortUrlRequestConverter.convertToEntity(shortUrlRequest);

        return new ResponseEntity<ShortUrlDto>(shortUrlDtoConverter.convertToDto(service.create(shortUrl)),HttpStatus.CREATED);
    }
}
