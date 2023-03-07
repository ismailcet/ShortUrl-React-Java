package com.ismailcet.urlShortener.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RandomStringGenerator {

    SecureRandom random = new SecureRandom();
    String generated = "";

    @Value("${codeLength}")
    private int codeLength;
    public String generateRandomString(){
       var letters = "abcdefghijklmnprstuvyzqw123456789"
               .toUpperCase()
               .chars()
               .mapToObj(x->(char)x)
               .collect(Collectors.toList());

        Collections.shuffle(letters);
        for(int i=0;i<codeLength;i++){
            generated +=letters.get(random.nextInt(letters.size()));
        }
        return generated;
    }
}
