package com.datacenter.GRH.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.datacenter.GRH.domain.models.Module; // 🔥 Importación corregida
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ModuleRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ModuleResponse;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    @Mapping(target = "id", ignore = true)
    Module toModule(ModuleRequest request); // 🔥 Se asegura de que usa 'Module'

    ModuleResponse toResponse(Module module); // 🔥 Se asegura de que usa 'Module'
}
