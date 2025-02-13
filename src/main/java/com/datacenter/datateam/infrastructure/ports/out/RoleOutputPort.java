package com.datacenter.datateam.infrastructure.ports.out;

import com.datacenter.datateam.domain.models.Role;

public interface RoleOutputPort {
    void save(Role role);
}
