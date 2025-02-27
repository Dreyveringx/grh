package com.datacenter.GRH.application.useCases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.CreateRoleRequest;
import com.datacenter.GRH.infrastructure.ports.out.RoleOutputPort;

@Component
@RequiredArgsConstructor
public class CreateRoleUseCase {
    private final RoleOutputPort roleOutputPort;

    public void execute(CreateRoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        roleOutputPort.save(role); // Guardar en la base de datos
    }
}
