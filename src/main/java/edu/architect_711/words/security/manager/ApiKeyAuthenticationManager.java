package edu.architect_711.words.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import edu.architect_711.words.security.provider.ApiKeyAuthenticationProvider;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ApiKeyAuthenticationManager implements AuthenticationManager {
    private final ApiKeyAuthenticationProvider provider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return provider.supports(authentication.getClass()) ? provider.authenticate(authentication) : authentication;
    }
}
