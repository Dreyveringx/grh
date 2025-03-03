package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.domain.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.createRole(role);
        return ResponseEntity.ok(newRole);
    }
}
