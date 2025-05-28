package com.kelompok3.rental_kendaraan_be.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret; // The secret key for signing the JWT (keep it safe)

    @Value("${jwt.expiration}")
    private long jwtExpirationMs; // Expiration time for JWT in milliseconds

    // Create a JWT token for the user
    public String generateToken(String username) {
        // Set the current date and expiration date
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        // Build the JWT token
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512) // Sign the token with the secret key
                .compact();
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject(); // Get the "subject" claim (username)
    }

    // Extract claims from the JWT token (including username, issuedAt, expiration, etc.)
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes()) // Set the secret key for parsing the token
                .build()
                .parseClaimsJws(token)
                .getBody(); // Return the claims body
    }

    // Validate the JWT token (check if it's expired or if it's signed correctly)
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Check if the JWT token is expired
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date()); // Check if the expiration date is before the current date
    }

    // Validate the token and return the username if valid
    public String validateTokenAndGetUsername(String token) {
        if (token != null && validateToken(token, extractUsername(token))) {
            return extractUsername(token); // Return the username if the token is valid
        }
        return null;
    }
}
