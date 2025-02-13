package com.datacenter.datateam.application.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.domain.services.RoleService;

@Component
@AllArgsConstructor
public class CreateRoleUseCase {
    private final RoleService roleService;

    public void execute(Role role) {
        roleService.createRole(role);
    }
}
