package com.universityteam.flashmemorizer.config;

import com.universityteam.flashmemorizer.customs.CustomLoginSuccessHandler;
import com.universityteam.flashmemorizer.customs.CustomLogoutSuccessHandler;
import com.universityteam.flashmemorizer.service.impl.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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


    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return  provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(withDefaults())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/css/**", "/js/**", "/images/**").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/home").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/login-process").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/logout").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/register-process").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/register-process/verifyEmail").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/{id}").hasAnyRole("USERS", "ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/edit/{id}").hasAnyRole("USERS", "ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/admin").hasRole("ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/**").authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/decks/**").permitAll())
                .formLogin(in -> in
                        .loginPage("/home")
                        .loginProcessingUrl("/login-process")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(loginSuccessHandler())
                        .failureUrl("/home?loginFailed")
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

    @Bean
    public ClassLoaderTemplateResolver secondaryTemplateResolver() {
        ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
        secondaryTemplateResolver.setPrefix("templates-2/");
        secondaryTemplateResolver.setSuffix(".html");
        secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
        secondaryTemplateResolver.setCharacterEncoding("UTF-8");
        secondaryTemplateResolver.setOrder(1);
        secondaryTemplateResolver.setCheckExistence(true);

        return secondaryTemplateResolver;
    }
}
