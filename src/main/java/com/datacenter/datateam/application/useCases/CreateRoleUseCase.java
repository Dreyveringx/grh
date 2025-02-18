package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.CreateRoleRequest;
import com.datacenter.datateam.infrastructure.ports.out.RoleOutputPort;
import com.datacenter.datateam.domain.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
