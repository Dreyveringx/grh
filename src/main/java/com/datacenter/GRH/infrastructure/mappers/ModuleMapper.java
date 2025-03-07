package com.datacenter.GRH.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import com.datacenter.GRH.domain.models.Modules;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    
    @org.mapstruct.Mapping(target = "id", ignore = true)  
    Modules toEntity(ModuleRequest dto);

    ModuleResponse toResponse(Modules entity);

    List<ModuleResponse> toResponseList(List<Modules> modules);
}
