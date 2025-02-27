package com.datacenter.GRH.infrastructure.adapters.out.databases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.datacenter.GRH.domain.models.Module; // 🔥 Importación corregida
import com.datacenter.GRH.infrastructure.ports.out.ModuleOutputPort;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ModuleRepositoryAdapter implements ModuleOutputPort {

    private final ModuleRepository moduleRepository; // 🔥 Se movió arriba para evitar errores

    @Override
    public Optional<Module> findByName(String name) {
        return moduleRepository.findByName(name);
    }

    @Override
    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Optional<Module> findById(Integer id) {
        return moduleRepository.findById(id);
    }

    @Override
    public void delete(Module module) {
        moduleRepository.delete(module);
    }
}
