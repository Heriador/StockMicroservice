package com.bootcamp2024.StockMicroservice.infrastructure.configuration.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secret_key;


    private Key generateKey() {

        return Keys.hmacShaKeyFor(secret_key.getBytes());
    }

    public String extractEmail(String jwt) {

        return extractAllClaims(jwt).getSubject();

    }

    public String extractRole(String jwt) {
        return extractAllClaims(jwt).get("role").toString();
    }



    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
    }

}
