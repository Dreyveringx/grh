package com.datacenter.GRH.application.useCases.module;

import org.springframework.stereotype.Service;

import com.datacenter.GRH.application.exceptions.BusinessException;
import com.datacenter.GRH.application.exceptions.ResourceNotFoundException;
import com.datacenter.GRH.domain.models.Modules;
import com.datacenter.GRH.infrastructure.adapters.out.databases.ModuleRepository;
import com.datacenter.GRH.infrastructure.adapters.out.databases.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteModuleUseCase {

    private final ModuleRepository moduleRepository;
    private final RoleRepository roleRepository;

    public void execute(Long id) {
        Modules module = moduleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Módulo no encontrado"));

            if (roleRepository.existsByModulesContaining(module)) {
                throw new BusinessException("No se puede eliminar el módulo porque está asociado a uno o más roles.");
            }            
        moduleRepository.deleteById(id);
    }
}

