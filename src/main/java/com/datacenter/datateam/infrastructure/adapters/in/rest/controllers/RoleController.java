package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.datateam.application.useCases.CreateRoleUseCase;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.CreateRoleRequest;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final CreateRoleUseCase createRoleUseCase;

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody CreateRoleRequest request) {
        createRoleUseCase.execute(request); // Ahora pasamos CreateRoleRequest correctamente
        return ResponseEntity.ok().build();
    }
}

