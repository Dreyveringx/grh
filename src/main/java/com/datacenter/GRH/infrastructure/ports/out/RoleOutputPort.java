package com.datacenter.GRH.infrastructure.ports.out;

import com.datacenter.GRH.domain.models.Role;
import java.util.Optional;

public interface RoleOutputPort {
    Optional<Role> findByName(String name);
    
    // ✅ Asegurar que este método devuelva Role en lugar de Optional<Role>
    Role save(Role role);
}
