package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.domain.services.UserService;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.PasswordResetRequest;
import com.datacenter.GRH.infrastructure.adapters.out.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PasswordRecoveryController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequest request) {
        if (request == null || request.getDocumentNumber() == null || request.getDocumentNumber().isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️ El número de documento es obligatorio.");
        }

        User user = userService.findByDocumentNumber(request.getDocumentNumber());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("⚠️ No existe un usuario con este número de documento.");
        }

        String token = jwtUtil.generateRecoveryToken(user.getDocumentNumber());

        return ResponseEntity.ok("🔑 Token de recuperación generado: " + token);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest request) {
        if (request.getToken() == null || request.getNewPassword() == null || request.getConfirmPassword() == null) {
            return ResponseEntity.badRequest().body("⚠️ Token y contraseñas son obligatorios.");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("⚠️ Las contraseñas no coinciden.");
        }

        try {
            // 🔥 Extraer el número de documento desde el token
            String documentNumber = jwtUtil.extractDocumentNumber(request.getToken());
            User user = userService.findByDocumentNumber(documentNumber);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("⚠️ Usuario no encontrado.");
            }

            // ✅ Encriptar la nueva contraseña antes de guardarla
            String encryptedPassword = passwordEncoder.encode(request.getNewPassword());
            user.setPassword(encryptedPassword);

            userService.updateUser(user); // ✅ Guardar la nueva contraseña en la base de datos

            return ResponseEntity.ok("✅ Contraseña actualizada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("⚠️ Token inválido o expirado.");
        }
    }
}
