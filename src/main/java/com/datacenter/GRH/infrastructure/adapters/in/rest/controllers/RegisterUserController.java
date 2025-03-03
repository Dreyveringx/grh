package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.GRH.application.useCases.RegisterUserUseCase;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.UserResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest request, HttpServletRequest httpRequest) {
        try {
            String authHeader = httpRequest.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(403).body("⛔ No se encontró un token JWT en la solicitud.");
            }
    
            String token = authHeader.substring(7); // Extraer solo el token sin "Bearer "
            System.out.println("✅ Token recibido en el controlador: " + token);
    
            UserResponse response = registerUserUseCase.execute(request, token);
    
            if (response == null) {
                return ResponseEntity.status(500).body("⚠️ No se pudo registrar el usuario.");
            }
    
            return ResponseEntity.ok(response);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body("⛔ " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error en el registro de usuario: " + e.getMessage());
            return ResponseEntity.status(500).body("Error en el servidor al procesar el registro.");
        }
    }
}    