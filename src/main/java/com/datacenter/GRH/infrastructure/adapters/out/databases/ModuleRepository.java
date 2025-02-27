package com.datacenter.GRH.infrastructure.adapters.out.databases;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.datacenter.GRH.domain.models.Module; // 🔥 Importación corregida

public interface ModuleRepository extends JpaRepository<Module, Integer> {
    Optional<Module> findByName(String name);
}
