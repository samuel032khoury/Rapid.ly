package com.rapidly.shortener.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    // private static final long serializableUID = 1L;
    private Long id;
    private String email;
    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static SecurityUser build(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new SecurityUser(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
