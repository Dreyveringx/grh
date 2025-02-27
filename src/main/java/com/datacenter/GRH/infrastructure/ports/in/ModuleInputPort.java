package com.datacenter.GRH.infrastructure.ports.in;

import java.util.List;

import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;

public interface ModuleInputPort {
    ModuleResponse createModule(ModuleRequest request);
    List<ModuleResponse> getAllModules();
    ModuleResponse getModuleById(Integer id);
    ModuleResponse updateModule(Integer id, ModuleRequest request);
    void deleteModule(Integer id);
}
