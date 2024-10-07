package edu.architect_711.words.security.config;

import edu.architect_711.words.security.filter.ApiKeyAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class ApiKeyAuthenticationConfiguration {
    @Autowired
    private ApplicationContext context;

    @Bean
    public SecurityFilterChain securityFilterChainAllAuthenticated(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .anyRequest().authenticated())
                .addFilterBefore((ApiKeyAuthenticationFilter) context.getBean("apiKeyAuthenticationFilter"), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
