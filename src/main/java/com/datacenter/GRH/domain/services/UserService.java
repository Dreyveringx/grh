package com.datacenter.GRH.domain.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.GRH.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.GRH.infrastructure.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario en la plataforma.
     */
    public UserResponse registerUser(RegisterUserRequest request) {
        // Verificar si el usuario ya existe por documento
        if (userRepository.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new RuntimeException("El usuario con este número de documento ya está registrado.");
        }

        // Verificar si el correo ya existe
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El usuario con este correo ya está registrado.");
        }

        // Crear y guardar el usuario
        User user = new User();
        user.setEmail(request.getEmail());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = new HashSet<>();  // 🔥 Asegúrate de inicializarlo
user.setRoles(roles);


        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    /**
     * Busca un usuario por su correo electrónico.
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario con email '" + email + "' no encontrado."));
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     */
    public void updateUser(User user) {
        userRepository.save(user);
    }

    /**
     * Busca un usuario por su número de documento.
     */
    public User findByDocumentNumber(String documentNumber) {
        return userRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(
                        () -> new RuntimeException("Usuario con documento '" + documentNumber + "' no encontrado."));
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
