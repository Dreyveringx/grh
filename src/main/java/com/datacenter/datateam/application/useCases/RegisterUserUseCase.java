package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.ports.in.RegisterUserInputPort;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;


import com.datacenter.datateam.application.exceptions.UserAlreadyExistsException;
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
        // Validar si el usuario ya existe
        if (userOutputPort.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este número de documento ya está registrado.");
        }

        // Validar si el correo ya existe
        if (userOutputPort.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este correo ya está registrado.");
        }
    
        // Crear usuario con valores por defecto
        User user = new User();
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
    
        // Valores por defecto
        user.setFirstName("Usuario");
        user.setLastName("No definido");
    
        // Asignar rol por defecto
        // Role defaultRole = roleRepository.findById(1)
        //     .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
        // user.setRoles(Collections.singletonList(defaultRole));
    
        // Guardar usuario
        user = userOutputPort.save(user);
    
        return userMapper.toResponse(user);
    }
}
    
