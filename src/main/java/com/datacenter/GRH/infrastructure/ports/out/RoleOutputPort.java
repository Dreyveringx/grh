package com.datacenter.GRH.infrastructure.ports.out;

import com.datacenter.GRH.domain.models.Role;

public interface RoleOutputPort {
    void save(Role role);
}
