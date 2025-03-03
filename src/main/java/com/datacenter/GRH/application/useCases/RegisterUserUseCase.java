package com.datacenter.GRH.application.useCases;


import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.datacenter.GRH.application.exceptions.UserAlreadyExistsException;
import com.datacenter.GRH.domain.models.Role;
import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.domain.services.EmailService;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import com.datacenter.GRH.infrastructure.adapters.out.databases.RoleRepository;
import com.datacenter.GRH.infrastructure.adapters.out.security.JwtUtil;
import com.datacenter.GRH.infrastructure.mappers.UserMapper;
import com.datacenter.GRH.infrastructure.ports.in.RegisterUserInputPort;
import com.datacenter.GRH.infrastructure.ports.out.UserOutputPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase implements RegisterUserInputPort {
    private final UserOutputPort userOutputPort;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public UserResponse execute(RegisterUserRequest request, String token) {
        System.out.println("🛠️ Token recibido: " + token);

        String adminDocumentNumber = jwtUtil.extractDocumentNumber(token);
        System.out.println("📌 Documento del Admin extraído del token: " + adminDocumentNumber);

        User adminUser = userOutputPort.findAdminWithCompanyAndRoles(adminDocumentNumber)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Admin no encontrado"));

        System.out.println("✅ Admin encontrado: " + adminUser.getDocumentNumber());

        boolean isAdmin = adminUser.getRoles().stream()
                .map(Role::getName)
                .anyMatch(roleName -> roleName.equalsIgnoreCase("ADMIN"));

        if (!isAdmin) {
            throw new SecurityException("⛔ Solo un administrador puede registrar usuarios.");
        }

        System.out.println("✅ Validación de rol exitosa. Continuando con el registro...");

        if (userOutputPort.findByDocumentNumber(request.getDocumentNumber()).isPresent()) {
            throw new UserAlreadyExistsException("⚠️ El usuario con este número de documento ya está registrado.");
        }
        if (userOutputPort.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("⚠️ El usuario con este correo ya está registrado.");
        }

        System.out.println("✅ Validaciones completadas. Creando usuario...");

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setDocumentNumber(request.getDocumentNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive(true);
        user.setCompany(adminUser.getCompany());

        if (request.getRole() != null) {
            Role role = roleRepository.findByName(request.getRole())
                    .orElseThrow(() -> new IllegalArgumentException("⚠️ El rol especificado no existe en la base de datos."));
            
            user.setRoles(Set.of(role)); // ✅ Ahora el rol ya existe en la base de datos antes de asignarlo
        } else {
            throw new IllegalArgumentException("⚠️ Debes asignar un rol al usuario.");
        }
        

        System.out.println("🛠️ Intentando guardar el usuario en la base de datos...");

        user = userOutputPort.save(user);

        if (user == null || user.getId() == null) {
            System.out.println("⚠️ No se pudo guardar el usuario en la base de datos.");
            throw new RuntimeException("Error al guardar el usuario en la base de datos.");
        }

        System.out.println("✅ Usuario guardado con ID: " + user.getId());

        emailService.sendEmail(user.getEmail(), "Bienvenido a la plataforma",
                String.format("Hola %s, tu contraseña es: %s", user.getFirstName(), request.getPassword()));

        System.out.println("📧 Correo de bienvenida enviado a: " + user.getEmail());

        UserResponse userResponse = userMapper.toResponse(user);

        if (userResponse == null) {
            System.out.println("⚠️ Error al mapear User a UserResponse.");
            throw new RuntimeException("Error al mapear el usuario.");
        }

        return userResponse;
    }
}
