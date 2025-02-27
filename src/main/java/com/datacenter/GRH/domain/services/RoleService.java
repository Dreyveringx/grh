package com.datacenter.GRH.domain.services;


import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.infrastructure.adapters.out.databases.RoleRepository;
import com.datacenter.GRH.infrastructure.ports.out.RoleOutputPort;

@Service
public class RoleService implements RoleOutputPort{
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
            this.roleRepository = roleRepository;
    }
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
    public void createRole(Role role) {
        throw new UnsupportedOperationException("Unimplemented method 'createRole'");
    }
}
