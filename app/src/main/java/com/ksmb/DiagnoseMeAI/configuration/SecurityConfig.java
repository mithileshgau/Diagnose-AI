package com.ksmb.DiagnoseMeAI.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF protection (not recommended for production)
            .authorizeRequests()
            .anyRequest().permitAll()  // Allow all requests without authentication
            .and()
            .formLogin().disable();  // Disable the default login form

        return http.build();
    }
}
