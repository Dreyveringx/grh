package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.ports.in.RegisterUserInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final RegisterUserInputPort registerUserInputPort;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest request) {
        // 🔥 Imprimir los datos recibidos en la consola
        log.info("📥 Recibiendo solicitud de registro: {}", request);
        // 🔥 También podemos imprimir cada campo por separado
        log.info("🆔 Documento: {}", request.getDocumentNumber());
        log.info("📧 Email: {}", request.getEmail());
        log.info("🔑 Contraseña: {}", request.getPassword()); // ⚠️ OJO: Solo para pruebas, no imprimir contraseñas en producción
 
        UserResponse response = registerUserInputPort.execute(request);
 
        // 🔥 Imprimir la respuesta antes de enviarla
        log.info("✅ Usuario registrado correctamente: {}", response);
 
        return ResponseEntity.ok(response);
    }
}
