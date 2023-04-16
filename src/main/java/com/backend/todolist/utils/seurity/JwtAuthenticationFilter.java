package com.backend.todolist.utils.seurity;

import com.backend.todolist.service.jwt.JwtService;
import com.backend.todolist.service.security.SecurityService;
import com.backend.todolist.utils.exception.Errors;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    SecurityService securityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) {
        try {
            if(request.getRequestURI().contains("/api/v1/auth/")) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtService.validateToken(jwt)) {
                Long userId = jwtService.getUsernameFromToken(jwt);
                UserDetails userDetails = securityService.loadUserById(userId);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken
                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if(e == Errors.UNAUTHORIZED) {
                response.setStatus(401);
            }
            else {
                response.setStatus(500);
            }
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw Errors.UNAUTHORIZED;
    }
}
