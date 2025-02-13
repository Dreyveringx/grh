package com.datacenter.datateam.domain.services;


import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.infrastructure.adapters.out.databases.RoleRepository;
import com.datacenter.datateam.infrastructure.ports.out.RoleOutputPort;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRole'");
    }
}
