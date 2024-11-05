package com.sugianto.nutech.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sugianto.nutech.exception.UnauthorizedException;
import com.sugianto.nutech.response.WebResponse;
import com.sugianto.nutech.service.JwtService;
import com.sugianto.nutech.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                throw new UnauthorizedException("Token tidak valid atau kadaluwarsa");
            }

            String jwt = authHeader.substring("Bearer ".length());

            if (jwtService.isExpired(jwt)) {
                filterChain.doFilter(request, response);
                throw new UnauthorizedException("Token tidak valid atau kadaluwarsa");
            }

            String username = jwtService.extractUsername(jwt);
            if (username != null) {
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            filterChain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            handleUnauthorizedException(response, e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/registration") || path.equals("/login");
    }

    private void handleUnauthorizedException(HttpServletResponse response, UnauthorizedException ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        WebResponse<Object> webResponse = WebResponse.builder()
                .status(108)
                .message(ex.getMessage())
                .data(null)
                .build();

        String jsonResponse = objectMapper.writeValueAsString(webResponse);

        response.getWriter().write(jsonResponse);
    }

}
