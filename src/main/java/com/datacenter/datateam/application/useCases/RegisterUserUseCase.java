package com.datacenter.datateam.application.useCases;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.datacenter.datateam.domain.models.*;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserOutputPort userOutputPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse execute(RegisterUserRequest request) {
        if (userOutputPort.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado.");
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userOutputPort.save(user); // ✅ Ahora usa el puerto de salida
        return userMapper.toResponse(user);
    }
}


