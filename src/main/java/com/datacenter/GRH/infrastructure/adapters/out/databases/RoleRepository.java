package com.datacenter.GRH.infrastructure.adapters.out.databases;

import com.datacenter.GRH.domain.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name); // ✅ Agregado
}
