package com.datacenter.datateam.infrastructure.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.datateam.application.AuthService;

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
}

