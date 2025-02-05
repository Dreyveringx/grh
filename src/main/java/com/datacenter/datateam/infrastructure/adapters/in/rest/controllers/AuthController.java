package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.datateam.application.exceptions.UsuarioInvalidoException;
import com.datacenter.datateam.application.useCases.AuthService;
import com.datacenter.datateam.domain.models.User;

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

        Optional<String> token = authService.authenticate(nuip, password);

        return token.map(jwt -> ResponseEntity.ok(Map.of("token", jwt)))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas")));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            authService.registerUser(user);
            return ResponseEntity.status(201).body("Usuario registrado exitosamente");
        } catch (UsuarioInvalidoException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

