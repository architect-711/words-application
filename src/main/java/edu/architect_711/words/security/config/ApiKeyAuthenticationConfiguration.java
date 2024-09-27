package edu.architect_711.words.security.config;

import edu.architect_711.words.security.filter.ApiKeyAuthenticationFilter;
import edu.architect_711.words.security.manager.ApiKeyAuthenticationManager;
import edu.architect_711.words.security.provider.ApiKeyAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ApiKeyAuthenticationConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .addFilterAt(apiKeyAuthenticationFilter(), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public ApiKeyAuthenticationFilter apiKeyAuthenticationFilter() {
        return new ApiKeyAuthenticationFilter(apiKeyAuthenticationManager());
    }

    @Bean
    public ApiKeyAuthenticationManager apiKeyAuthenticationManager() {
        return new ApiKeyAuthenticationManager(apiKeyAuthenticationProvider());
    }

    @Bean
    public ApiKeyAuthenticationProvider apiKeyAuthenticationProvider() {
        return new ApiKeyAuthenticationProvider();
    }
}
