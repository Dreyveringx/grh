package com.datacenter.datateam.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.datateam.application.AuthService;
import com.datacenter.datateam.domain.model.User;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String nuip = credentials.get("nuip");
        String password = credentials.get("password");

        if (nuip == null || password == null || nuip.isEmpty() || password.isEmpty()) {
            return ResponseEntity.status(400).body(Map.of("error", "El nuip y la contraseña son obligatorios"));
        }

        Optional<String> token = authService.authenticate(nuip, password);

        return token.map(jwt -> ResponseEntity.ok(Map.of("token", jwt)))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas")));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        System.out.println("Datos recibidos: " + user);

        if (user.getNuip() == null || user.getNuip().isEmpty()) {
            return ResponseEntity.status(400).body("El campo 'nuip' es obligatorio");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body("El campo 'password' es obligatorio");
        }

        boolean userCreated = authService.registerUser(user);

        if (userCreated) {
            return ResponseEntity.status(201).body("Usuario registrado exitosamente");
        } else {
            return ResponseEntity.status(400).body("El usuario ya existe");
        }
    }

}
