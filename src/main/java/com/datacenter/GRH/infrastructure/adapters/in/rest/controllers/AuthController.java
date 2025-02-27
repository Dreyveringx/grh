package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import com.datacenter.GRH.application.useCases.AuthenticateUserUseCase;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authenticateUserUseCase.execute(request);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas. Verifica tu número de documento y contraseña.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en el servidor al procesar la autenticación.");
        }
    }
}
