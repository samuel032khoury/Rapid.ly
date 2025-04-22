package com.rapidly.shortener.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidly.shortener.dto.UrlMappingDTO;
import com.rapidly.shortener.models.User;
import com.rapidly.shortener.service.UrlMappingService;
import com.rapidly.shortener.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {
    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> shortenUrl(@RequestBody Map<String, String> request, Principal principal) {
        String originalUrl = request.get("originalUrl");
        User user = userService.findUserByUsername(principal.getName());
        UrlMappingDTO urlMappingDTO = urlMappingService.shortenUrl(originalUrl, user);
        if (urlMappingDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(urlMappingDTO);
    }
}