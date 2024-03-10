package com.brainstation.BrainStationAPI.utility;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "vQQK1OcnbWL8Ob8ZIcRU2Rcy4MA7E3mv44xZomyEeng=";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}