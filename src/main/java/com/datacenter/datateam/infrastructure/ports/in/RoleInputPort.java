package com.datacenter.datateam.infrastructure.ports.in;

import com.datacenter.datateam.domain.models.Role;

public interface RoleInputPort {
    void createRole(Role role);
}
