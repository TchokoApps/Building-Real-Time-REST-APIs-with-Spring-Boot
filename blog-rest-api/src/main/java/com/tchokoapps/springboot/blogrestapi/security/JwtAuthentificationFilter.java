package com.tchokoapps.springboot.blogrestapi.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private JwtTockenProvider jwtTockenProvider;

    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);
        if (isTockenValid(token, jwtTockenProvider.validateJwtTocken(token))) {
            String username = jwtTockenProvider.getUsernameFromJwt(token);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
        }

    }

    private boolean isTockenValid(String tocken, boolean b) {
        return StringUtils.hasText(tocken) && b;
    }

    private String getJwtFromRequest(HttpServletRequest httpServletRequest) {
        String bearerTocken = httpServletRequest.getHeader("Authorization");
        if (isTockenValid(bearerTocken, bearerTocken.startsWith("Bearer "))) {
            return bearerTocken.substring(7, bearerTocken.length());
        }
        return null;

    }
}
