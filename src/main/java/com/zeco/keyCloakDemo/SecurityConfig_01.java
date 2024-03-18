package com.zeco.keyCloakDemo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig_01{

    private final JwtAuthConverter_02 jwtAuthConverter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( req -> req
                        .anyRequest()
                        .authenticated() );

        http
                .oauth2ResourceServer( oauth2 -> oauth2
                        .jwt( jwt -> jwt.jwtAuthenticationConverter( jwtAuthConverter )) );

        http
                .sessionManagement( session -> session
                        .sessionCreationPolicy(STATELESS));



        return http.build();
    }
}