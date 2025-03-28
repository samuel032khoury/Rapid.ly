package com.rapidly.shortener.security.jwt;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {
    private String token;
}
