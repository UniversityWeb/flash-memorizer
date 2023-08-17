package com.universityteam.flashmemorizer.config;

import com.universityteam.flashmemorizer.handlers.CustomLoginSuccessHandler;
import com.universityteam.flashmemorizer.handlers.CustomLogoutSuccessHandler;
import com.universityteam.flashmemorizer.service.impl.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
    // authentication
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails admin = User.withUsername("username1")
//                .password(encoder.encode("pass2"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("username2")
//                .password(encoder.encode("pass2"))
//                .roles("USERS")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(withDefaults())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/home").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/{id}").hasAnyRole("USERS", "ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/admin").hasRole("ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/decks/**").permitAll())
                .formLogin(in -> in
                        .loginProcessingUrl("/home")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successForwardUrl("/user/{id}")
                        .successHandler(loginSuccessHandler())
                        .permitAll())
                .logout(out -> out
                        .logoutSuccessUrl("/home")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .exceptionHandling(except -> except.accessDeniedPage("/access-denied"))
                .build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/richtext/**");
    }
}
