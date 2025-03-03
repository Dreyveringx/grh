package com.datacenter.GRH.application.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;
import com.datacenter.GRH.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.GRH.infrastructure.adapters.out.security.JwtUtil;
import com.datacenter.GRH.infrastructure.ports.in.AuthInputPort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthenticateUserUseCase implements AuthInputPort {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse execute(LoginRequest request) {
        System.out.println("🔍 Iniciando autenticación para: " + request.getDocumentNumber());

        User user = userRepository.findByDocumentNumber(request.getDocumentNumber())
                .orElseThrow(() -> new BadCredentialsException("Usuario o contraseña incorrectos."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            System.err.println("❌ Contraseña incorrecta para: " + request.getDocumentNumber());
            throw new BadCredentialsException("Usuario o contraseña incorrectos.");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getDocumentNumber());

        // 🔍 Verificar que los roles se están obteniendo correctamente
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        System.out.println("✅ Roles obtenidos para el usuario " + user.getDocumentNumber() + ": " + roles);

        // ❗ Aquí agregamos una validación extra antes de generar el token
        if (roles.isEmpty()) {
            System.err.println("⚠️ Error: El usuario no tiene roles asignados. No se puede generar un token.");
            throw new IllegalStateException("El usuario no tiene roles asignados.");
        }

        // 🔑 Generar el token
        System.out.println("🔑 Generando token con roles: " + roles);
        String token = jwtUtil.generateToken(userDetails, user.getDocumentNumber(), roles);

        // 📌 Imprimir el token generado
        System.out.println("📝 Token generado: " + token);

        return new LoginResponse(token, user);
    }
}