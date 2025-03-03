package com.datacenter.GRH.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.datacenter.GRH.infrastructure.adapters.out.security.JwtAuthenticationFilter;
import com.datacenter.GRH.domain.services.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter; // 🔥 Filtro JWT
    private final CustomUserDetailsService userDetailsService; // ✅ Servicio de autenticación

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder()); // ✅ Encriptación de contraseñas
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 🔥 Desactivamos CSRF porque usamos JWT
            .cors(cors -> cors.disable()) // ✅ Configuramos CORS de forma separada
            .authorizeHttpRequests(auth -> auth
                // 🔓 Endpoints públicos
                .requestMatchers(
                    "/api/users/register", 
                    "/api/auth/login", 
                    "/api/password/update",
                    "/api/forgot-password",
                    "/api/reset-password",
                    "/api/modules/**"
                ).permitAll()

                // 🔐 Protección de rutas según el rol
                .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/organization/**").hasAuthority("ROLE_ORGANIZATION_ADMIN")
                .requestMatchers("/api/employee/**").hasAuthority("ROLE_EMPLOYEE")
                .requestMatchers("/api/candidate/**").hasAuthority("ROLE_CANDIDATE")

                // 🔐 Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🚀 JWT es sin estado
            .authenticationProvider(authenticationProvider()) // ✅ Configurar autenticación
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // 🔥 Agregar filtro JWT

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 🔑 Para encriptar contraseñas
    }

    // ✅ CORS Configurado de manera más flexible
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // Frontend permitido
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
