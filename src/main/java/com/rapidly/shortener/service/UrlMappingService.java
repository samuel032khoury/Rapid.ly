package com.rapidly.shortener.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rapidly.shortener.dto.ClickEventDTO;
import com.rapidly.shortener.dto.UrlMappingDTO;
import com.rapidly.shortener.models.ClickEvent;
import com.rapidly.shortener.models.UrlMapping;
import com.rapidly.shortener.models.User;
import com.rapidly.shortener.repository.ClickEventRepository;
import com.rapidly.shortener.repository.UrlMappingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final ClickEventRepository clickEventRepository;

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

    public List<UrlMappingDTO> getUrlsByUser(User user) {
        List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
        if (urlMappings.isEmpty()) {
            return null;
        }
        return urlMappings.stream()
                .map(urlMapping -> new UrlMappingDTO(
                        urlMapping.getId(),
                        urlMapping.getOriginalUrl(),
                        urlMapping.getShortUrl(),
                        urlMapping.getClickCount(),
                        urlMapping.getCreatedDate(),
                        user.getUsername()))
                .toList();
    }

    public List<ClickEventDTO> getUrlClickEventsWithin(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping == null) {
            return null;
        }
        return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping, start, end)
                .stream()
                .collect(Collectors.groupingBy(clickEvent -> clickEvent.getClickDate().toLocalDate(),
                        Collectors.counting()))
                .entrySet().stream()
                .map(entry -> new ClickEventDTO(
                        entry.getKey(),
                        entry.getValue()))
                .toList();
    }

    public Map<LocalDate, Long> getTotalClicksByUser(User user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
        if (urlMappings.isEmpty()) {
            return null;
        }
        List<ClickEvent> clickEvents = clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings,
                start.atStartOfDay(),
                end.plusDays(1).atStartOfDay());
        return clickEvents.stream()
                .collect(Collectors.groupingBy(clickEvent -> clickEvent.getClickDate().toLocalDate(),
                        Collectors.counting()));
    }

    public String getOriginalUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
            // side effect: increment click count and save click event
            urlMapping.setClickCount(urlMapping.getClickCount() + 1);
            urlMappingRepository.save(urlMapping);
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setUrlMapping(urlMapping);
            clickEvent.setClickDate(LocalDateTime.now());
            clickEventRepository.save(clickEvent);
            return urlMapping.getOriginalUrl();
        }
        return null;
    }
}
