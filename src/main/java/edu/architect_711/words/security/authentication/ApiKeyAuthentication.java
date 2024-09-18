package edu.architect_711.words.security.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyAuthentication implements Authentication {
    private boolean isAuthenticated = false;
    private final String API_KEY;

    public ApiKeyAuthentication(boolean isAuthenticated, String API_KEY) {
        this.isAuthenticated = isAuthenticated;
        this.API_KEY = API_KEY;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public Object getCredentials() {
        return API_KEY;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }
    
}
