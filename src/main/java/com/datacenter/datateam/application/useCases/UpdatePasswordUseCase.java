package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.UpdatePasswordRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.PasswordUpdateResponse;
import com.datacenter.datateam.infrastructure.ports.in.PasswordInputPort;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordUseCase implements PasswordInputPort {

    private static final Logger logger = LoggerFactory.getLogger(UpdatePasswordUseCase.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PasswordUpdateResponse updatePassword(UpdatePasswordRequest request) {
        logger.info("Actualizando contraseña para documentNumber: {}", request.getDocumentNumber());

        // Buscar usuario en la base de datos
        User user = userRepository.findByDocumentNumber(request.getDocumentNumber())
                .orElseThrow(() -> {
                    logger.warn("Usuario no encontrado: {}", request.getDocumentNumber());
                    return new RuntimeException("Usuario no encontrado");
                });

        // Validar la contraseña actual
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            logger.warn("Contraseña actual incorrecta para usuario: {}", request.getDocumentNumber());
            throw new BadCredentialsException("Contraseña actual incorrecta");
        }

        // Encriptar la nueva contraseña y actualizar
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        logger.info("Contraseña actualizada con éxito para usuario: {}", request.getDocumentNumber());

        return new PasswordUpdateResponse("Contraseña actualizada correctamente");
    }
}
