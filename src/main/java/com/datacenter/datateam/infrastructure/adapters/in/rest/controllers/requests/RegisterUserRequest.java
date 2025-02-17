package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;  // Obligatorio

    @NotNull(message = "El tipo de documento es obligatorio")
    private Integer documentTypeId;  // Obligatorio

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;  // Obligatorio

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;  // Obligatorio

    // Los demás campos no son obligatorios

    private String firstName;  // No obligatorio
    private String lastName;  // No obligatorio
    private String birthDate;  // No obligatorio
    private String documentIssueDate;  // No obligatorio
    private Integer documentIssueCityId;  // No obligatorio
    private Integer nationalityId;  // No obligatorio
    private Integer maritalStatusId;  // No obligatorio
    private boolean hasHouse;  // No obligatorio
    private boolean hasChildren;  // No obligatorio
    private String secondaryEmail;  // No obligatorio
    private String phone;  // No obligatorio
    private Integer companyId;  // No obligatorio
    private Integer positionId;  // No obligatorio
    private Integer statusId;  // No obligatorio
    private List<Integer> roleIds;  // No obligatorio
}
