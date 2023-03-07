package com.ismailcet.urlShortener.repository;

import com.ismailcet.urlShortener.Entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepsitory extends JpaRepository<ShortUrl,Long> {

    Optional<ShortUrl> findAllByCode(String code);
}
