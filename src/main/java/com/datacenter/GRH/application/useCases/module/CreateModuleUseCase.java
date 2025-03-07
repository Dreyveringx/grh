package com.datacenter.GRH.application.useCases.module;
 
import org.springframework.stereotype.Service;

import com.datacenter.GRH.application.exceptions.BusinessException;
import com.datacenter.GRH.domain.models.Modules;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;
import com.datacenter.GRH.infrastructure.adapters.out.databases.ModuleRepository;
import com.datacenter.GRH.infrastructure.mappers.ModuleMapper;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class CreateModuleUseCase {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    public ModuleResponse execute(ModuleRequest request) {
        if (moduleRepository.findByNameIgnoreCase(request.getName()).isPresent()) {
            throw new BusinessException("El módulo con el nombre '" + request.getName() + "' ya existe.");
        }        

        Modules module = moduleMapper.toEntity(request);
        Modules savedModule = moduleRepository.save(module);

        return moduleMapper.toResponse(savedModule);
    }
}
