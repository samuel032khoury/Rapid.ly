package com.rapidly.shortener.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTAuthenticationResponse {
    private String token;
}
