package com.datacenter.datateam.infrastructure.mappers;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.RoleRepository;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected RoleRepository roleRepository;

    @Mapping(source = "documentTypeId", target = "documentType.id")
    @Mapping(source = "documentIssueCityId", target = "documentIssueCity.id")
    @Mapping(source = "nationalityId", target = "nationality.id")
    @Mapping(source = "maritalStatusId", target = "maritalStatus.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "positionId", target = "position.id")
    @Mapping(source = "statusId", target = "status.id")
    @Mapping(target = "roles", expression = "java(mapRoles(request.getRoleIds()))") // Mapeo correcto de roles
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "email2", ignore = true)
    @Mapping(target = "idDocumentType", ignore = true)

    public abstract User toUser(RegisterUserRequest request);

    @Mapping(source = "documentType.name", target = "documentType")
    @Mapping(source = "nationality.name", target = "nationality")
    @Mapping(source = "maritalStatus.name", target = "maritalStatus")
    @Mapping(source = "company.name", target = "company")
    @Mapping(source = "position.name", target = "position")
    @Mapping(source = "status.name", target = "status")
    @Mapping(target = "roles", expression = "java(mapRoleNames(user.getRoles()))") // Mapeo correcto de nombres de roles
    public abstract UserResponse toResponse(User user);

    // Método para mapear lista de roleIds a lista de objetos Role
    protected List<Role> mapRoles(List<Integer> roleIds) {
        return roleIds == null ? null : roleRepository.findAllById(roleIds);
    }

    // Método para mapear lista de objetos Role a lista de nombres de roles
    protected List<String> mapRoleNames(List<Role> roles) {
        return roles == null ? null : roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
