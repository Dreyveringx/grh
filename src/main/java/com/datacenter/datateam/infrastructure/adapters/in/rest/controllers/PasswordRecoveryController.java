package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import com.datacenter.datateam.application.useCases.FindUserByEmailUseCase;
import com.datacenter.datateam.application.useCases.UpdateUserUseCase;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.domain.services.EmailService;
import com.datacenter.datateam.domain.services.UserService;
import com.datacenter.datateam.infrastructure.adapters.out.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class PasswordRecoveryController {

    private final FindUserByEmailUseCase findUserByEmailUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️ El email es obligatorio.");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("⚠️ No existe un usuario con este email.");
        }

        // Generar un token de recuperación (válido por 15 min)
        String token = jwtUtil.generateRecoveryToken(user.getEmail());

    // Enviar email con el enlace de recuperación apuntando al frontend de Angular
    String resetLink = "http://localhost:4200/restablecer?token=" + token;
    emailService.sendEmail(user.getEmail(), "Recuperación de contraseña",
            "Haz clic en el siguiente enlace para restablecer tu contraseña: " + resetLink);

        return ResponseEntity.ok("📩 Se ha enviado un correo con instrucciones.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");
        String confirmPassword = request.get("confirmPassword");

        if (token == null || newPassword == null || confirmPassword == null) {
            return ResponseEntity.badRequest().body("⚠️ Token y contraseñas son obligatorios.");
        }

        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("⚠️ Las contraseñas no coinciden.");
        }

        // Extraer email desde el token
        String email;
        try {
            email = jwtUtil.extractUsername(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("⚠️ Token inválido o expirado.");
        }

        // Buscar usuario por email
        User user = findUserByEmailUseCase.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("⚠️ Usuario no encontrado.");
        }

        // Actualizar contraseña
        user.setPassword(passwordEncoder.encode(newPassword));
        updateUserUseCase.updateUser(user);

        return ResponseEntity.ok("✅ Contraseña actualizada correctamente.");
    }
}
