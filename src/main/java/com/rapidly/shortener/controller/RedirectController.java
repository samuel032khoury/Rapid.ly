package com.rapidly.shortener.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rapidly.shortener.service.UrlMappingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RedirectController {
    private final UrlMappingService urlMappingService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) throws URISyntaxException {
        String originalURL = urlMappingService.getOriginalUrl(shortUrl);
        if (originalURL == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .status(302)
                .location(new URI(originalURL))
                .build();
    }
}