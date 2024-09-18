package edu.architect_711.words.security.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import edu.architect_711.words.security.authentication.ApiKeyAuthentication;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    @Value("${test.security.key.value}")
    private String testSecurityKeyValue;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String requestApiKeyValue = authentication.getCredentials().toString();

        if (!requestApiKeyValue.equals(testSecurityKeyValue))
            throw new BadCredentialsException("Provided authentication key is not valid.");
        return new ApiKeyAuthentication(true, requestApiKeyValue);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
