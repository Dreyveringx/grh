package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;
import com.datacenter.datateam.infrastructure.ports.in.AuthInputPort;
import com.datacenter.datateam.infrastructure.adapters.out.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticateUserUseCase implements AuthInputPort {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public LoginResponse execute(LoginRequest request) {
        // Autenticación del usuario
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getDocumentNumber(), request.getPassword()));
        } catch (Exception e) {
            // Manejo de error en caso de fallo de autenticación
            throw new RuntimeException("Invalid credentials");
        }

        // Establecer el contexto de seguridad con la autenticación
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Cargar detalles del usuario autenticado
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getDocumentNumber());

        // Obtener roles del usuario
        List<String> roles = userDetails.getAuthorities().stream()
            .map(authority -> authority.getAuthority().replace("ROLE_", "")) // Quitar el prefijo ROLE_
            .collect(Collectors.toList());

        // Generar el token JWT
        String jwt = jwtUtil.generateToken(userDetails);

        // Retornar respuesta con JWT y roles
        return new LoginResponse(jwt, roles);
    }
}
