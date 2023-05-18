package com.backend.todolist.service.jwt;

import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.utils.exception.Errors;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JwtServiceImpl implements JwtService {
    private final String JWT_SECRET = "VuNAProPTIT";

    @Override
    public String generateToken(UserDetailEntity userDetail) {
        Date now = new Date();
        long jwtExpiration = (long) 15 * 60 * 1000;
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(userDetail.getAccount().getUserId().toString())
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    @Override
    public Long getUserIdFromToken(String token) {
        return Long.parseLong(Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    @Override
    public String getUserNameFromToken(String token) {
        try {
            validateToken(token);
            return Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw Errors.TOKEN_EXPIRED;
        }

    }

    @Override
    public String generateRefreshToken(UserDetailEntity userDetail) {
        Date now = new Date();
        long jwtExpiration = (long) 30 * 24 * 60 * 60 * 1000;
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(userDetail.getAccount().getUsername())
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
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
