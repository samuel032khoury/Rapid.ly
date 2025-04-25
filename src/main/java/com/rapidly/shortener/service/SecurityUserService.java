package com.rapidly.shortener.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rapidly.shortener.models.SecurityUser;
import com.rapidly.shortener.models.User;
import com.rapidly.shortener.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecurityUserService implements UserDetailsService {
    UserRepository userRepository;

    @Override
    @Transactional
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return SecurityUser.build(user);
    }

}
