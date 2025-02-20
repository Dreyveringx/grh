package com.datacenter.datateam.domain.services;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.RoleRepository;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterUserRequest request) {
        // Verificar si el usuario ya existe
        if (userRepository.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new RuntimeException("El usuario con este número de documento ya está registrado.");
        }

        // Asignar rol por defecto (siempre se asigna uno)
        Role defaultRole = roleRepository.findById(1)
            .orElseThrow(() -> new RuntimeException("Rol por defecto no encontrado"));

        // Crear usuario con valores mínimos requeridos
        User user = new User();1
        user.setEmail(request.getEmail());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singletonList(defaultRole));

        // Opcionalmente, agregar valores por defecto en otros campos
        user.setFirstName("Usuario");
        user.setLastName("No definido");

        // Guardar usuario
        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }
}
