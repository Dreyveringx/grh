package com.datacenter.GRH.infrastructure.ports.in;

import com.datacenter.GRH.domain.models.Role;

public interface RoleInputPort {
    void createRole(Role role);
}
