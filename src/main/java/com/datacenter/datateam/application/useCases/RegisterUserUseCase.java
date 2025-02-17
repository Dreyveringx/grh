package com.datacenter.datateam.application.useCases;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;
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

        // Obtener entidades relacionadas
        Parameter documentType = findEntityById(parameterRepository, request.getDocumentTypeId(), "Tipo de documento no encontrado.");
        Parameter maritalStatus = findEntityById(parameterRepository, request.getMaritalStatusId(), "Estado civil no encontrado.");
        City city = findEntityById(cityRepository, request.getDocumentIssueCityId(), "Ciudad de emisión no encontrada.");
        Nationality nationality = findEntityById(nationalityRepository, request.getNationalityId(), "Nacionalidad no encontrada.");
        UserStatus status = findEntityById(userStatusRepository, request.getStatusId(), "Estado de usuario no encontrado.");

        // Obtener compañía y posición solo si están presentes en la solicitud
        Company company = request.getCompanyId() != null ? 
            findEntityById(companyRepository, request.getCompanyId(), "Compañía no encontrada.") : null;
        Position position = request.getPositionId() != null ? 
            findEntityById(positionRepository, request.getPositionId(), "Posición no encontrada.") : null;

        // Obtener Roles
        List<Role> roles = roleRepository.findAllById(request.getRoleIds());
        if (roles.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron los roles proporcionados.");
        }

        // Mapear DTO a entidad User
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encriptar contraseña
        user.setDocumentType(documentType);
        user.setDocumentIssueCity(city);
        user.setNationality(nationality);
        user.setMaritalStatus(maritalStatus);
        user.setCompany(company);
        user.setPosition(position);
        user.setStatus(status);
        user.setRoles(roles);

        // Guardar usuario en la base de datos
        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }

    // ✅ Método genérico para encontrar entidades y evitar código repetitivo
    private <T, ID> T findEntityById(JpaRepository<T, ID> repository, ID id, String errorMessage) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}
