package com.datacenter.datateam.application.useCases;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Component;

import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.DocumentTypeRepository;
import com.datacenter.datateam.infrastructure.adapters.out.databases.RoleRepository;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;

import com.datacenter.datateam.application.exceptions.DocumentTypeNotFoundException;
import com.datacenter.datateam.application.exceptions.RoleNotFoundException;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    // private final CityRepository cityRepository;
    // private final NationalityRepository nationalityRepository;
    // private final ParameterRepository parameterRepository;
    // private final CompanyRepository companyRepository;
    // private final PositionRepository positionRepository;
    // private final UserStatusRepository userStatusRepository;

    public UserResponse execute(RegisterUserRequest request) {
        // Buscar DocumentType o lanzar excepción si no existe
        DocumentType documentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new DocumentTypeNotFoundException("Tipo de documento no encontrado."));

        // City city = cityRepository.findById(request.getDocumentIssueCityId())
        //         .orElseThrow(() -> new CityNotFoundException("Ciudad de emisión no encontrada."));

        // Buscar roles o lanzar excepción si no se encuentran
        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        if (roles.isEmpty()) {
            throw new RoleNotFoundException("No se encontraron los roles proporcionados.");
        }

        // Mapear DTO a entidad User
        User user = userMapper.toUser(request);
        user.setDocumentType(documentType);
        // user.setDocumentIssueCity(city); 
        user.setRoles(roles);

        // Guardar usuario en la base de datos
        user = userRepository.save(user);

        // Convertir entidad a DTO de respuesta
        return userMapper.toResponse(user);
    }
}
