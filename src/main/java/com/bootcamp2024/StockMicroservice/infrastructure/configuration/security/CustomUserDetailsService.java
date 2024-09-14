package com.bootcamp2024.StockMicroservice.infrastructure.configuration.security;

import com.bootcamp2024.StockMicroservice.infrastructure.output.mysql.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String jwt) throws UsernameNotFoundException {
        String email = jwtService.extractEmail(jwt);
        String role = jwtService.extractAuthorities(jwt);


        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

        return new User(email,"", authorities);
    }
}
