package com.banking.smart_bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // Use NoOpPasswordEncoder for plain-text passwords
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and() // Enable CORS so that frontend and backend can communicate at different ports.
            .csrf(csrf -> csrf.disable())   // Disable CSRF for simplicity; consider enabling in production
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login").permitAll()  // Allow access to login endpoint
              //  .requestMatchers("/api/test/**").permitAll()  // Temporarily allow all access to /api/test for testing
                .requestMatchers("/api/accounts/user/**").authenticated()  // Require authentication for account endpoints
                .requestMatchers("/api/transfer/internal").authenticated()
                .requestMatchers("/api/transfer/external").authenticated()
                .anyRequest().authenticated()  // All other endpoints require authentication
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

