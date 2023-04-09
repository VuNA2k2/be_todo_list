package com.backend.todolist.utils.provider;

import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.utils.exception.Errors;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    private final String JWT_SECRET = "VuNAProPTIT";
    private final Long JWT_EXPIRATION = 604800000L;

    public String generateToken(UserDetailEntity userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(userDetails.getAccount().getUserId().toString())
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        return Long.parseLong(Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public Boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException | IllegalArgumentException | UnsupportedJwtException ex) {
            throw Errors.INVALID_TOKEN;
        } catch (ExpiredJwtException ex) {
            throw Errors.TOKEN_EXPIRED;
        }
    }
}
