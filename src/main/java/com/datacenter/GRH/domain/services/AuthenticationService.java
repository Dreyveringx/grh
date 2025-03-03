package com.datacenter.GRH.domain.services;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.GRH.infrastructure.adapters.out.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public String generateToken(String documentNumber) {
        System.out.println("🔍 Buscando usuario en la BD para generar token: " + documentNumber);

        User user = userRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new IllegalArgumentException("❌ Usuario no encontrado: " + documentNumber));

        // 📌 Obtener roles del usuario
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        System.out.println("✅ Roles obtenidos para el usuario " + documentNumber + ": " + roles);

        UserDetails userDetails = userDetailsService.loadUserByUsername(documentNumber);

        // 📌 Generar el token con los roles incluidos
        String token = jwtUtil.generateToken(userDetails, documentNumber, roles);

        System.out.println("✅ Token generado correctamente: " + token);
        return token;
    }
}
