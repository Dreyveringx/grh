package com.datacenter.datateam.domain.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.infrastructure.ports.out.RoleOutputPort;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleOutputPort roleOutputPort;

    public void createRole(Role role) {
        // Lógica de negocio para crear un rol
        roleOutputPort.save(role);
    }
}
