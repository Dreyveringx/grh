package com.datacenter.GRH.application.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Module;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;
import com.datacenter.GRH.infrastructure.mappers.ModuleMapper;
import com.datacenter.GRH.infrastructure.ports.in.ModuleInputPort;
import com.datacenter.GRH.infrastructure.ports.out.ModuleOutputPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageModulesUseCase implements ModuleInputPort {

    private final ModuleOutputPort moduleOutputPort;
    private final ModuleMapper moduleMapper;

    @Override
    public ModuleResponse createModule(ModuleRequest request) {
        if (moduleOutputPort.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("El módulo con este nombre ya existe.");
        }

        Module module = moduleMapper.toModule(request);
        module = moduleOutputPort.save(module);
        
        return moduleMapper.toResponse(module);
    }

    @Override
    public List<ModuleResponse> getAllModules() {
        return moduleOutputPort.findAll().stream()
                .map(moduleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ModuleResponse getModuleById(Integer id) {
        Module module = moduleOutputPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
        return moduleMapper.toResponse(module);
    }

    @Override
    public ModuleResponse updateModule(Integer id, ModuleRequest request) {
        Module module = moduleOutputPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Módulo no encontrado"));

        module.setName(request.getName());
        module.setDescription(request.getDescription());

        module = moduleOutputPort.save(module);
        return moduleMapper.toResponse(module);
    }

    @Override
    public void deleteModule(Integer id) {
        Module module = moduleOutputPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Módulo no encontrado"));
        moduleOutputPort.delete(module);
    }
}
