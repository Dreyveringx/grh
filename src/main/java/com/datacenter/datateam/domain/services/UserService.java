package com.datacenter.datateam.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.domain.models.Role;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.DocumentTypeRepository;
import com.datacenter.datateam.infrastructure.adapters.out.databases.RoleRepository;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DocumentTypeRepository documentTypeRepository;
    // private final CityRepository cityRepository;
    // private final NationalityRepository nationalityRepository;
    // private final ParameterRepository parameterRepository;
    // private final CompanyRepository companyRepository;
    // private final PositionRepository positionRepository;
    private final RoleRepository roleRepository;
    // private final UserStatusRepository userStatusRepository;
    private final UserMapper userMapper;

    public UserResponse registerUser(RegisterUserRequest request) {
        DocumentType documentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new RuntimeException("DocumentType no encontrado"));

        // City city = cityRepository.findById(request.getDocumentIssueCityId())
        //         .orElseThrow(() -> new RuntimeException("City no encontrada"));

        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        if (roles.isEmpty()) {
            throw new RuntimeException("Roles no encontrados");
        }

        User user = userMapper.toUser(request);
        user.setDocumentType(documentType);
        // user.setDocumentIssueCity(city);
        user.setRoles(roles);
        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }
}

