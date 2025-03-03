package com.datacenter.GRH.infrastructure.adapters.out.databases;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.infrastructure.ports.out.RoleOutputPort;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary // ✅ Esto indica a Spring que esta es la implementación principal
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleOutputPort {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
