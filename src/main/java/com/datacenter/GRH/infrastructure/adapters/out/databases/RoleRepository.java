package com.datacenter.GRH.infrastructure.adapters.out.databases;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.GRH.domain.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    // Métodos adicionales de consulta pueden ir aquí
}
