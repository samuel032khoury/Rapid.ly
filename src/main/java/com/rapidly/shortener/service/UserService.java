package com.rapidly.shortener.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rapidly.shortener.models.SecurityUser;
import com.rapidly.shortener.models.User;
import com.rapidly.shortener.repository.UserRepository;
import com.rapidly.shortener.security.jwt.JWTAuthenticationResponse;
import com.rapidly.shortener.security.jwt.JWTUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JWTUtils jwtUtils;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public JWTAuthenticationResponse authenticateUser(String username, String password) {
        UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(u);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(securityUser);
        return new JWTAuthenticationResponse(jwt);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
