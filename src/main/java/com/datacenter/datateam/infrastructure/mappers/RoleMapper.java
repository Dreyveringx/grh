package com.datacenter.datateam.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.CreateRoleRequest;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Role toRole(CreateRoleRequest request);
}
