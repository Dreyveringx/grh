package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;
import com.datacenter.datateam.infrastructure.ports.in.AuthInputPort;
import com.datacenter.datateam.infrastructure.adapters.out.security.JwtUtil;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AuthenticateUserUseCase implements AuthInputPort {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository; // Agregamos el repositorio para buscar el usuario
    private final PasswordEncoder passwordEncoder; // Agregamos el PasswordEncoder

    @Override
    public LoginResponse execute(LoginRequest request) {
        System.out.println("Buscando usuario con documentNumber: " + request.getDocumentNumber());

        // Buscar usuario en la base de datos
        User user = userRepository.findByDocumentNumber(request.getDocumentNumber())
                .orElseThrow(() -> {
                    System.out.println("Usuario no encontrado en la BD.");
                    return new RuntimeException("Usuario no encontrado");
                });

        System.out.println("Usuario encontrado: " + user.getDocumentNumber());
        System.out.println("Contraseña ingresada: " + request.getPassword());
        System.out.println("Contraseña en BD: " + user.getPassword());
        System.out.println("¿Coinciden? " + passwordEncoder.matches(request.getPassword(), user.getPassword()));

       

        // Autenticar con Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getDocumentNumber(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getDocumentNumber());

        
        String jwt = jwtUtil.generateToken(userDetails);

        return new LoginResponse(jwt);
    }
}
