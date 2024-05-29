package com.foodapi.demo.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.foodapi.demo.services.CustomUserDetail;

@Component
public class JwtUtil {
    @Autowired
    CustomUserDetail userDetailsService;

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
                .setExpiration(new Date(System.currentTimeMillis() + 10*60*60*1000))
                .signWith(getSigningkey(),SignatureAlgorithm.HS512)
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
    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpiration(String jwt){
        Date date = Jwts.parserBuilder()
                                .setSigningKey(getSigningkey())
                                .build()
                                .parseClaimsJws(jwt)
                                .getBody()
                                .getExpiration();
        return date;
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

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromJwt(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
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
