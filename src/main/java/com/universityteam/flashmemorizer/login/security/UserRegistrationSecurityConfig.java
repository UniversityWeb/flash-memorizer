package com.universityteam.flashmemorizer.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEndcoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/register/**")
                        .permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("users")
                        .hasAnyAuthority("USER", "ADMIN"))
                .formLogin(Customizer.withDefaults()).build();
    }
}
