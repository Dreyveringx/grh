package com.datacenter.GRH.infrastructure.adapters.out.databases;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.GRH.domain.models.Modules;

@Repository
public interface ModuleRepository extends JpaRepository<Modules, Long> {
    Optional<Modules> findByName(String name);
    Optional<Modules> findByNameIgnoreCase(String name);
}
