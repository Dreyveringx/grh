package com.datacenter.GRH.application.useCases;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.infrastructure.ports.out.RoleOutputPort;

@Component
@RequiredArgsConstructor
public class CreateRoleUseCase {

    private final @Qualifier("roleRepositoryAdapter") RoleOutputPort roleOutputPort;

    public Role execute(Role role) {
        return roleOutputPort.save(role);
    }
}
