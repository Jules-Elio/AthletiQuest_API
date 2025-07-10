package com.athletiquest.athletiquest_api.auth;

import com.athletiquest.athletiquest_api.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String ADMIN = RoleType.ADMIN.name();
    private static final String USER = RoleType.USER.name();
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/stadiums/retrieve").hasAuthority(ADMIN);
            authorize.requestMatchers("/login", "/register", "/stadiums", "/stadiums/*").permitAll();
            authorize.requestMatchers("/*/current").hasAnyAuthority(ADMIN, USER);
            authorize.requestMatchers(HttpMethod.DELETE).hasAuthority(ADMIN);
            authorize.requestMatchers(HttpMethod.GET).hasAnyAuthority(ADMIN, USER);
            authorize.requestMatchers(HttpMethod.POST).hasAnyAuthority(ADMIN, USER);
            authorize.anyRequest().hasAnyAuthority(ADMIN, USER);
        }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
