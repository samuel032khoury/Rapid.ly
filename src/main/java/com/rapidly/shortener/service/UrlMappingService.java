package com.rapidly.shortener.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.rapidly.shortener.dto.UrlMappingDTO;
import com.rapidly.shortener.models.UrlMapping;
import com.rapidly.shortener.models.User;
import com.rapidly.shortener.repository.UrlMappingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlMappingDTO shortenUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
        return new UrlMappingDTO(
                savedUrlMapping.getId(),
                savedUrlMapping.getOriginalUrl(),
                savedUrlMapping.getShortUrl(),
                savedUrlMapping.getClickCount(),
                savedUrlMapping.getCreatedDate(),
                user.getUsername());
    }

    private String generateShortUrl() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            shortUrl.append(chars.charAt(random.nextInt(chars.length())));
        }
        return shortUrl.toString();
    }

}
