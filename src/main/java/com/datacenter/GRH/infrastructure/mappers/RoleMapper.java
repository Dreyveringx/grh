package com.datacenter.GRH.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.CreateRoleRequest;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "modules", ignore = true) // ✅ Agregado
    @Mapping(target = "permissions", ignore = true) // ✅ Agregado
    Role toRole(CreateRoleRequest request);
}
