package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.datateam.application.useCases.CreateRoleUseCase;
import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.CreateRoleRequest;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private final CreateRoleUseCase createRoleUseCase;
    private final RoleMapper roleMapper;

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody CreateRoleRequest request) {
        Role role = roleMapper.toRole(request);
        createRoleUseCase.execute(role);
        return ResponseEntity.ok().build();
    }
}
