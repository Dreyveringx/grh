package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.ports.in.RegisterUserInputPort;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUserInputPort {
    private final UserOutputPort userOutputPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse execute(RegisterUserRequest request) {
        // 1️⃣ Validar si el usuario ya existe
        if (userOutputPort.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("❌ El email ya está registrado.");
        }

        if (userOutputPort.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new RuntimeException("❌ El número de documento ya está registrado.");
        }

        // 2️⃣ Convertir request a User
        User user = userMapper.toUser(request);
        
        // 3️⃣ Encriptar contraseña
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 4️⃣ Guardar usuario en la base de datos
        user = userOutputPort.save(user);

        // 5️⃣ Devolver la respuesta con datos del usuario registrado
        return userMapper.toResponse(user);
    }
}
