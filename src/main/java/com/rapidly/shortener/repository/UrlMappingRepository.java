package com.rapidly.shortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rapidly.shortener.models.UrlMapping;
import com.rapidly.shortener.models.User;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortUrl(String shortUrl);

    List<UrlMapping> findByUser(User user);

    boolean existsByShortUrl(String shortUrl);
}
