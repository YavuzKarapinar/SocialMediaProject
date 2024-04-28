package me.jazzy.socialmediaproject.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtGenerator {

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .signWith(generateKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private  <T> T extractClaims(String token, Function<Claims, T> function) {
        Claims claims = extractAllClaims(token);
        return function.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isValidToken(String token) {
        try {
            if(!isTokenExpired(token)) {
                Jwts.parser()
                        .verifyWith(generateKey())
                        .build()
                        .parseSignedClaims(token);
                return true;
            }
        } catch (JwtException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    private SecretKey generateKey() {
        String secretKey = "jahsjasdkhuıasd78123ahjsd!'^1234";
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}