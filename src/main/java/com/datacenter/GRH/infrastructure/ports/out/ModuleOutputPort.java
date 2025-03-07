package com.datacenter.GRH.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import com.datacenter.GRH.domain.models.Modules;

public interface ModuleOutputPort {
    Module save(Modules modules);
    List<Modules> findAll();
    Optional<Modules> findById(Integer id);
    Optional<Modules> findByName(String name);
    void delete(Modules modules);
}
