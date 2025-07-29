package com.valkyrie.api_gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class TokenConfiguration{
    private static final int EXPIRATION = 1000 * 60 * 60 * 30;
    @Value("${jwts.security}")
    private String securityKey;

    private Key generateKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));
    }

    private <I> I generateClaim(String token, Function<Claims, I> getClaims) {
        Claims claims = Jwts.parserBuilder().setSigningKey(generateKey()).build()
                            .parseClaimsJws(token).getBody();
        return getClaims.apply(claims);
    }

    private boolean isExpired(String token) {
        return !generateClaim(token, Claims::getExpiration).before(new Date());
    }

    public String generateToken(String username,
                                Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claim = new HashMap<>();
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).toList();

        claim.put("roles", roles);

        return Jwts.builder().setClaims(claim).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(generateKey()).compact();
    }

    public String getUsername(String token) {
        return generateClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(getUsername(token)) && isExpired(token);
    }
}
