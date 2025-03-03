package com.datacenter.GRH.infrastructure.mappers;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.*;
import com.datacenter.GRH.domain.models.*;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.UserResponse;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    // 📌 Convertir `RegisterUserRequest` a `User`
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "firstName", source = "firstName") // 🔥 Se asigna desde el request
    @Mapping(target = "lastName", source = "lastName")   // 🔥 Se asigna desde el request
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roles", ignore = true)  // Se asignará después manualmente
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "documentIssueCity", ignore = true)
    @Mapping(target = "documentIssueDate", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "email2", ignore = true)
    @Mapping(target = "hasChildren", ignore = true)
    @Mapping(target = "hasHouse", ignore = true)
    @Mapping(target = "maritalStatus", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "activationToken", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "resetToken", ignore = true) 
    public abstract User toUser(RegisterUserRequest request);

    // 📌 Convertir `User` a `UserResponse`
    @Mapping(target = "roles", expression = "java(mapRoleNames(user.getRoles()))") // ✅ Asigna roles correctamente
    public abstract UserResponse toResponse(User user);

    // 🔥 Método para mapear los roles correctamente
    protected List<String> mapRoleNames(List<Role> roles) {
        return (roles == null || roles.isEmpty()) ? List.of() : roles.stream().map(Role::getName).collect(Collectors.toList());
    }

    // Métodos para convertir objetos a String
    protected String mapCompany(Company company) {
        return company != null ? company.getName() : null;
    }

    protected String mapNationality(Nationality nationality) {
        return nationality != null ? nationality.getName() : null;
    }

    protected String mapStatus(UserStatus status) {
        return status != null ? status.getName() : null;
    }

    protected String mapParameter(Parameter parameter) {
        return parameter != null ? parameter.getName() : null;
    }

    protected String mapPosition(Position position) {
        return position != null ? position.getName() : null;
    }
}
