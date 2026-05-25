package com.sgrh.sgrh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                }) // Activa CORS con la configuración por defecto de Spring
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/clientes/registrar", "/api/v1/clientes/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(basic -> {
                }); // Activa la autenticación básica con valores por defecto

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// se debe aplicar el nuevo versionamiento.
// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http
// .csrf(csrf -> csrf.disable())
// .cors(cors -> cors.and())
// .sessionManagement(session ->
// session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// .authorizeHttpRequests(authz -> authz
// .requestMatchers("/api/v1/clientes/registrar",
// "/api/v1/clientes/login").permitAll()
// .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
// .anyRequest().authenticated())
// .httpBasic(basic -> basic.and());

// return http.build();
// }

// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http
// .csrf(csrf -> csrf.disable())
// .cors(cors -> cors.withDefaults()) // Arregla la línea 20: usa withDefaults()
// en lugar de .and()
// .sessionManagement(session ->
// session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// .authorizeHttpRequests(authz -> authz
// .requestMatchers("/api/v1/clientes/registrar",
// "/api/v1/clientes/login").permitAll()
// .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
// .anyRequest().authenticated())
// .httpBasic(basic -> basic.withDefaults());

// return http.build();
// }