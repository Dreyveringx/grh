package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.application.exceptions.UserAlreadyExistsException;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.domain.services.EmailService;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;
import com.datacenter.datateam.infrastructure.ports.in.RegisterUserInputPort;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUserInputPort {
    private final UserOutputPort userOutputPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService; // Inyectamos el servicio de email

    @Override
    public UserResponse execute(RegisterUserRequest request) {
        // Validar si el usuario ya existe
        if (userOutputPort.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este número de documento ya está registrado.");
        }

        if (userOutputPort.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este correo ya está registrado.");
        }

        // Crear usuario
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName("Usuario");
        user.setLastName("No definido");

        // Guardar usuario en la base de datos
        user = userOutputPort.save(user);

        // Enviar correo con las credenciales
        String subject = "Bienvenido a nuestra plataforma";
        String body = String.format(
            "Hola,\n\nTu registro fue exitoso.\n\nTus credenciales son:\n- Usuario: %s\n- Contraseña: %s\n\nSaludos,",
            user.getDocumentNumber(), request.getPassword() // Se envía la contraseña original
        );

        emailService.sendEmail(user.getEmail(), subject, body);

        return userMapper.toResponse(user);
    }
}

    
