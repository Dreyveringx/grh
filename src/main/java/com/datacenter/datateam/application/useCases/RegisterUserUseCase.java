package com.datacenter.datateam.application.useCases;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

import com.datacenter.datateam.application.exceptions.ResourceNotFoundException;
import com.datacenter.datateam.domain.models.*;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.*;
import com.datacenter.datateam.infrastructure.mappers.UserMapper;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final ParameterRepository parameterRepository;
    private final CityRepository cityRepository;
    private final NationalityRepository nationalityRepository;
    private final CompanyRepository companyRepository;
    private final PositionRepository positionRepository;
    private final RoleRepository roleRepository;
    private final UserStatusRepository userStatusRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse execute(RegisterUserRequest request) {
        // Verificar si el email ya existe
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado.");
        }

        // Obtener Tipo de Documento (Parameter)
        Parameter documentType = parameterRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado."));

        // Obtener Estado Civil (Parameter)
        Parameter maritalStatus = parameterRepository.findById(request.getMaritalStatusId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado civil no encontrado."));

        // Obtener Ciudad de Emisión
        City city = cityRepository.findById(request.getDocumentIssueCityId())
                .orElseThrow(() -> new ResourceNotFoundException("Ciudad de emisión no encontrada."));

        // Obtener Nacionalidad
        Nationality nationality = nationalityRepository.findById(request.getNationalityId())
                .orElseThrow(() -> new ResourceNotFoundException("Nacionalidad no encontrada."));

        // Obtener Compañía (si se proporciona)
        Company company = request.getCompanyId() != null
                ? companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Compañía no encontrada."))
                : null;

        // Obtener Posición (si se proporciona)
        Position position = request.getPositionId() != null
                ? positionRepository.findById(request.getPositionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Posición no encontrada."))
                : null;

        // Obtener Estado del Usuario
        UserStatus status = userStatusRepository.findById(request.getStatusId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de usuario no encontrado."));

        // Obtener Roles
        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        if (roles.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron los roles proporcionados.");
        }

        // Mapear DTO a entidad User
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ENCRIPTAR CONTRASEÑA
        user.setDocumentType(documentType);
        user.setDocumentIssueCity(city);
        user.setNationality(nationality);
        user.setMaritalStatus(maritalStatus);
        user.setCompany(company);
        user.setPosition(position);
        user.setStatus(status);
        user.setRoles(roles);

        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }
}
