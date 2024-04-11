package com.foodapi.demo.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secretKey;

    public Key getSigningkey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 60))
                .signWith(getSigningkey())
                .compact();

    }

    public boolean validateJwtToken(String jwt) {
        try {

            Jwts.parserBuilder().setSigningKey(getSigningkey()).build().parseClaimsJws(jwt);
            return true;
        } catch (ExpiredJwtException exception){
            exception.getMessage();
        
        } catch (JwtException | IllegalArgumentException e) {
            e.getMessage();
        }
        return false;
    }

    public boolean checkExpiration(String token){
        Date date = Jwts.parserBuilder()
                        .setSigningKey(getSigningkey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getExpiration();
        return date.before(Date.from(Instant.now()));
    }

    public String getUsernameFromJwt(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningkey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
