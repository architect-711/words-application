package edu.architect_711.words.security.filter;

import edu.architect_711.words.security.authentication.ApiKeyAuthentication;
import edu.architect_711.words.security.manager.ApiKeyAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
    @Value("${test.security.key.title}") @NonNull
    private String testSecurityKeyTitle;
    private final ApiKeyAuthenticationManager manager;

    public ApiKeyAuthenticationFilter(ApiKeyAuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String REQUEST_API_KEY = request.getHeader(testSecurityKeyTitle);
        final Authentication authentication = manager.authenticate(new ApiKeyAuthentication(false, REQUEST_API_KEY));

        if (!authentication.isAuthenticated()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
