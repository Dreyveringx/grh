package com.datacenter.GRH.application.useCases.module;

import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Modules;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;
import com.datacenter.GRH.infrastructure.adapters.out.databases.ModuleRepository;
import com.datacenter.GRH.infrastructure.mappers.ModuleMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindModuleByIdUseCase {
    
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;
    
    public ModuleResponse execute(Long id) {
        Modules modules = moduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
        
        return moduleMapper.toResponse(modules);
    }
}
