package com.backend.todolist.service.jwt;

import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.utils.exception.Errors;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JwtServiceImpl implements JwtService {
    private final String JWT_SECRET = "VuNAProPTIT";
    private final Long JWT_EXPIRATION = 604800000L;

    @Override
    public String generateToken(UserDetailEntity userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(userDetails.getAccount().getUserId().toString())
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    @Override
    public Long getUsernameFromToken(String token) {
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
