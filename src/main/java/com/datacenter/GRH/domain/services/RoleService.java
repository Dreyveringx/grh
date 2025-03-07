package com.datacenter.GRH.domain.services;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.infrastructure.ports.out.RoleOutputPort;
import com.datacenter.GRH.infrastructure.adapters.out.databases.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleOutputPort {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role); // ✅ Asegurar que devuelve Role
    }
    public Role createRole(Role role) {
        return roleRepository.save(role); // ✅ Método agregado
    }
    
}
