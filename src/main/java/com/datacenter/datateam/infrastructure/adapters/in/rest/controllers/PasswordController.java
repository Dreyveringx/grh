package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import com.datacenter.datateam.application.exceptions.UserNotFoundException;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.domain.services.EmailService;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ForgotPasswordRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ResetPasswordRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.UpdatePasswordRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.PasswordUpdateResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.ports.in.PasswordInputPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordInputPort passwordInputPort;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @PutMapping("/update")
    public ResponseEntity<PasswordUpdateResponse> updatePassword(
            @Validated @RequestBody UpdatePasswordRequest request) {
        return ResponseEntity.ok(passwordInputPort.updatePassword(request));
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        User user = userOpt.get();
        String token = UUID.randomUUID().toString(); // Generar token único
        user.setResetToken(token);
        userRepository.save(user);

        String resetLink = "http://localhost:3000/reset-password?token=" + token;
        emailService.sendEmail(user.getEmail(), "Restablecer contraseña",
                "Haga clic en el siguiente enlace para restablecer su contraseña: " + resetLink);

        return ResponseEntity.ok("Correo enviado con éxito.");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        Optional<User> userOpt = userRepository.findByResetToken(request.getToken());

        if (userOpt.isEmpty()) {
            throw new BadCredentialsException("Token inválido o expirado");
        }

        User user = userOpt.get();
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setResetToken(null); // Eliminar el token después de usarlo
        userRepository.save(user);

        return ResponseEntity.ok("Contraseña actualizada con éxito.");
    }

}
