package com.ismailcet.urlShortener.service;

import com.ismailcet.urlShortener.Entity.ShortUrl;
import com.ismailcet.urlShortener.exception.CodeAlreadyExists;
import com.ismailcet.urlShortener.exception.ShortUrlNotFoundException;
import com.ismailcet.urlShortener.repository.ShortUrlRepsitory;
import com.ismailcet.urlShortener.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {
    private final ShortUrlRepsitory shortUrlRepsitory;
    private final RandomStringGenerator randomStringGenerator;
    public ShortUrlService(ShortUrlRepsitory shortUrlRepsitory, RandomStringGenerator randomStringGenerator) {
        this.shortUrlRepsitory = shortUrlRepsitory;
        this.randomStringGenerator = randomStringGenerator;
    }

    public List<ShortUrl> getAllShortUrl(){
        return shortUrlRepsitory.findAll();
    }

    public ShortUrl getUrlByCode(String code) {

        return shortUrlRepsitory.findAllByCode(code).orElseThrow(
                ()-> new ShortUrlNotFoundException("Url Not Found ! ")
        );
    }

    public ShortUrl create(ShortUrl shortUrl) {
        if(shortUrl.getCode() ==null || shortUrl.getCode().isEmpty()){
            shortUrl.setCode(generateCode());
        }
        else if(shortUrlRepsitory.findAllByCode(shortUrl.getCode()).isPresent()){
            throw new CodeAlreadyExists("Code already exists");
        }

        shortUrl.setCode(shortUrl.getCode().toUpperCase());

        return shortUrlRepsitory.save(shortUrl);
    }

    protected String generateCode(){
        String code;
        do{
            code = randomStringGenerator.generateRandomString();
        }
        while (shortUrlRepsitory.findAllByCode(code).isPresent());
        return code;
    }
}
