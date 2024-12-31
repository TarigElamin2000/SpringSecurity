package com.example.security.config;

import com.example.security.Services.JwtToken;
import com.example.security.Services.MyUserDetailes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtFillter extends OncePerRequestFilter{

    @Autowired
    private final JwtToken jwtService;
    private final ApplicationContext context;

    public JwtFillter(JwtToken jwtService, ApplicationContext context) {
        this.jwtService = jwtService;
        this.context = context;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String strToken  = request.getHeader("Authorization");
    String token = null;
    String username = null;

    if(strToken != null && strToken.startsWith("Bearer ")){
        token = strToken.substring(7);
        username = jwtService.extractUserName(token);
    }

    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

        UserDetails userDetails = context.getBean(MyUserDetailes.class).loadUserByUsername(username);

        if(jwtService.validateToken(token, userDetails)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
    filterChain.doFilter(request, response);

    }
}

