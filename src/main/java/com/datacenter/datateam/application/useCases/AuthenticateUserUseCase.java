package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;
import com.datacenter.datateam.infrastructure.ports.in.AuthInputPort;
import com.datacenter.datateam.infrastructure.adapters.out.security.JwtUtil;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse execute(LoginRequest request) {
        System.out.println("🔍 Buscando usuario con documentNumber: " + request.getDocumentNumber());

        // Buscar usuario en la base de datos
        User user = userRepository.findByDocumentNumber(request.getDocumentNumber())
                .orElseThrow(() -> {
                    return new BadCredentialsException("Usuario o contraseña incorrectos.");
                });

        System.out.println("✅ Usuario encontrado: " + user.getDocumentNumber());

        // Verificar si la contraseña coincide
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Usuario o contraseña incorrectos.");
        }

        // Intentar autenticar con Spring Security
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getDocumentNumber(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getDocumentNumber());

            // Generar token JWT
            String jwt = jwtUtil.generateToken(userDetails);
            System.out.println("✅ Login exitoso. Token generado.");

            return new LoginResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Usuario o contraseña incorrectos.");
        } catch (Exception e) {
            throw new RuntimeException("Error en el servidor al procesar la autenticación.");
        }
        
    }
}
