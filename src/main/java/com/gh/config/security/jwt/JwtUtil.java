package com.gh.config.security.jwt;

import com.gh.services.common.domain.User;
import com.gh.services.users.domain.Users;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    @Value("${study.jwt.key}")
    private String key ;

    @Value("${study.jwt.expiration}")
    private int expirationMs;

    /**
     * Authentication 구현 후 변경할 것
     *
     * @Param Users loginUser -> Authentication authentication
     *
     */
    public String generateJwtToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();

        Map<String, Object > header = new HashMap<>();
        header.put("type", "jwt");
        header.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();

        payloads.put("id", userPrincipal.getId());
        payloads.put("username", userPrincipal.getUsername());

        return Jwts.builder()
                .setHeader(header)
                .setSubject(userPrincipal.getUsername())
                .setClaims(payloads)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }

    public String getUsernameByToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch ( SignatureException e) {
            log.error("invalid JWT Signature : {} ", e.getMessage());
        } catch ( MalformedJwtException e) {
            log.error("invalid JWT : {} ", e.getMessage());
        } catch ( ExpiredJwtException e) {
            log.error("JWT is expired : {} ", e.getMessage());
        } catch ( UnsupportedJwtException e) {
            log.error("JWT is unsupported : {} ", e.getMessage());
        } catch ( IllegalArgumentException e) {
            log.error("JWT is wrong : {} ", e.getMessage());
        }
        return false;
    }
}
