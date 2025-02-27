package com.datacenter.GRH.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import com.datacenter.GRH.domain.models.Module; // 🔥 Importación corregida

public interface ModuleOutputPort {
    Module save(Module module);
    List<Module> findAll();
    Optional<Module> findById(Integer id);
    Optional<Module> findByName(String name);
    void delete(Module module);
}
