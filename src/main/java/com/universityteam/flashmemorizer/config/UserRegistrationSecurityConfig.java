package com.universityteam.flashmemorizer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class UserRegistrationSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    // authentication
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("root")
                .password(encoder.encode("root"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("username2")
                .password(encoder.encode("pass2"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//    authenticationProvider.setUserDetailsService(userDetailsService());
//    authenticationProvider.setPasswordEncoder(passwordEncoder());
//    return authenticationProvider;
//}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.cors(withDefaults())
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/css/**")
//                        .permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/decks/**")
//                        .permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/register/**")
//                        .permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("users")
//                        .hasAnyAuthority("USER", "ADMIN"))
//                .formLogin(withDefaults()).build();
//        return http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//                .formLogin(auth -> auth.permitAll())
//                .logout(auth -> auth.permitAll())
//                .build();
        return http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/home").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").authenticated())
                .formLogin(auth -> auth.usernameParameter("username").passwordParameter("pass_hash"))
                .build();
//        return http
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/home", "/user/new").permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/customer/**").authenticated() )
//                .formLogin(withDefaults()).build();
    }
}
