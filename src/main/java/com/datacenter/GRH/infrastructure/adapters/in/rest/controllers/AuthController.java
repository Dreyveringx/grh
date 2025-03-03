package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.GRH.application.useCases.AuthenticateUserUseCase;
import com.datacenter.GRH.domain.services.AuthenticationService;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // 📌 Ejecutamos el caso de uso para autenticar el usuario
            LoginResponse response = authenticateUserUseCase.execute(request);
            
            // 📌 Generamos el token JWT
            String jwt = authenticationService.generateToken(request.getDocumentNumber());

            // 📌 Retornamos el token junto con el usuario autenticado
            return ResponseEntity.ok(new LoginResponse(jwt, response.getUser()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciales incorrectas. Verifica tu número de documento y contraseña.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en el servidor al procesar la autenticación.");
        }
    }
}
