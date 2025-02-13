package com.datacenter.datateam.infrastructure.adapters.out.databases;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.infrastructure.ports.out.RoleOutputPort;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, RoleOutputPort {
    // Métodos adicionales de consulta pueden ir aquí
}
