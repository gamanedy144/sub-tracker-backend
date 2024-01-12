package com.dissertation.subtrackerbackend.config;

import com.dissertation.subtrackerbackend.domain.Role;
import com.dissertation.subtrackerbackend.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "Cr1ERGInjU78c5lto15mBNWwXW4VVUwZyNuvPTZW6zMHAgNSMYENwnGKCXq5nZw9Xdc5sV0Wuj4WV7aoWuKwcrwnTkn13QWwRj5R6aiujAHEmE21R1x0ukCChYDAGIqsVuqa+AifzypAjzHhKusM/xiiahdNtTuawYtoGZf5aHyb7RoE9ALJunsMbcsZgSFKqukrWrqB+mGW6rggl4e+i+zX9z6iSM3Zy8dBsgNnJATJiaLm7VoelrokzBAlW6TnPNZj1Us2YKX1zgei/1mU9UmEiKZuLjKlyA2BtCuwQUsKJzX9eOL9yz6P6f5cUpNGbOve35JQwajj54SkYyUAHGR17p+a4e3ZSpIz7akAoYs=";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        String fullName = ((User) userDetails).getFullName();
        Role role = ((User) userDetails).getRole();
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("fullName", fullName);
        extraClaims.put("role", role);
        return generateToken(extraClaims, userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 3600 * 24 * 30))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            String username = ((User) authentication.getPrincipal()).getEmail();
            return username;
        }
        return null;
    }

    public String getRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            Role role = ((User) authentication.getPrincipal()).getRole();
            return role.toString();
        }
        return null;
    }
}
