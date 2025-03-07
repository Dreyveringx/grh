package com.datacenter.GRH.application.useCases.module;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Modules;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;
import com.datacenter.GRH.infrastructure.adapters.out.databases.ModuleRepository;
import com.datacenter.GRH.infrastructure.mappers.ModuleMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllModulesUseCase {

    private final ModuleRepository modulesRepository;
    private final ModuleMapper moduleMapper;

    public List<ModuleResponse> execute() {
        List<Modules> modules = modulesRepository.findAll();
        return moduleMapper.toResponseList(modules);
    }
}
