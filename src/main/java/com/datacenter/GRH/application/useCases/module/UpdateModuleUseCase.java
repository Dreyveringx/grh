package com.datacenter.GRH.application.useCases.module;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.datacenter.GRH.application.exceptions.BusinessException;
import com.datacenter.GRH.application.exceptions.ResourceNotFoundException;
import com.datacenter.GRH.domain.models.Modules;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;
import com.datacenter.GRH.infrastructure.adapters.out.databases.ModuleRepository;
import com.datacenter.GRH.infrastructure.mappers.ModuleMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateModuleUseCase {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    @SuppressWarnings("unlikely-arg-type")
    public ModuleResponse execute(Long id, ModuleRequest request) {
        Modules existingModule = moduleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Módulo no encontrado"));

        Optional<Modules> duplicateModule = moduleRepository.findByNameIgnoreCase(request.getName());
        if (duplicateModule.isPresent() && !Objects.equals(duplicateModule.get().getId(), id)) {  
            throw new BusinessException("Ya existe un módulo con el nombre '" + request.getName() + "'");
        }

        existingModule.setName(request.getName());
        existingModule.setDescription(request.getDescription());

        Modules updatedModule = moduleRepository.save(existingModule);
        return moduleMapper.toResponse(updatedModule);
    }
}
