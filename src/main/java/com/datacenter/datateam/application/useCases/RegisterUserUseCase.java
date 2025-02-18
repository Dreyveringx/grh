package com.datacenter.datateam.application.useCases;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;
import com.datacenter.datateam.application.exceptions.UserAlreadyExistsException;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserOutputPort userOutputPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse execute(RegisterUserRequest request) {
        if (userOutputPort.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El email ya está registrado.");
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userOutputPort.save(user);
        return userMapper.toResponse(user);
    }
}
