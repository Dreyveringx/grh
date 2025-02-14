package com.datacenter.datateam.infrastructure.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "documentTypeId", target = "documentType.id")
    @Mapping(source = "documentIssueCityId", target = "documentIssueCity.id")
    @Mapping(source = "nationalityId", target = "nationality.id")
    @Mapping(source = "maritalStatusId", target = "maritalStatus.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "positionId", target = "position.id")
    @Mapping(source = "statusId", target = "status.id")
    @Mapping(source = "roleIds", target = "roles")
    User toUser(RegisterUserRequest request);

    @Mapping(source = "documentType.name", target = "documentType")
    @Mapping(source = "nationality.name", target = "nationality")
    @Mapping(source = "maritalStatus.name", target = "maritalStatus")
    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "position.name", target = "position")
    @Mapping(source = "status.name", target = "status")
    @Mapping(source = "roles", target = "roles")
    UserResponse toResponse(User user);

    default List<Role> mapRoles(List<Integer> roleIds) {
        return roleIds == null ? null : roleIds.stream().map(id -> new Role(id, null, null)).collect(Collectors.toList());
    }

    default List<String> mapRoleNames(List<Role> roles) {
        return roles == null ? null : roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
