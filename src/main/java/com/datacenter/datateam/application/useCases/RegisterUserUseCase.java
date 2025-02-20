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

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUserInputPort {
    private final UserOutputPort userOutputPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public UserResponse execute(RegisterUserRequest request) {
        // Validar si el usuario ya existe
        if (userOutputPort.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este número de documento ya está registrado.");
        }

        if (userOutputPort.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El usuario con este correo ya está registrado.");
        }

        // Generar una contraseña segura aleatoria (si no la proporciona)
        String generatedPassword = UUID.randomUUID().toString().substring(0, 8); // 8 caracteres
        String encodedPassword = passwordEncoder.encode(generatedPassword);

        // Crear usuario con estado INACTIVO
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPassword(encodedPassword);
        user.setIsActive(true); // Asegúrate de que exista el setter en User
 // Desactivado hasta activación

        // Guardar usuario en la base de datos
        user = userOutputPort.save(user);

        // Generar token de activación
        String activationToken = UUID.randomUUID().toString();
        user.setActivationToken(activationToken);
        userOutputPort.save(user);

        // Enviar correo con el enlace de activación
        String activationLink = "http://localhost:8085/auth/activate?token=" + activationToken;
        String subject = "Activa tu cuenta en nuestra plataforma";
        String body = String.format(
            "Hola %s,\n\nTu registro fue exitoso. Para activar tu cuenta, haz clic en el siguiente enlace:\n\n%s\n\n"
            + "Tu contraseña temporal es: %s (cámbiala después de iniciar sesión).\n\nSaludos.",
            user.getFirstName(), activationLink, generatedPassword
        );

        emailService.sendEmail(user.getEmail(), subject, body);

        return userMapper.toResponse(user);
    }
}
