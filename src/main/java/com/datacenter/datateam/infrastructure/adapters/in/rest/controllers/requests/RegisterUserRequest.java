package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;

    @NotNull(message = "El tipo de documento es obligatorio")
    private Integer documentTypeId;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    private String firstName;
    private String lastName;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate birthDate;  // Usando LocalDate

    @Past(message = "La fecha de emisión del documento debe ser en el pasado")
    private LocalDate documentIssueDate;  // Usando LocalDate

    private Integer documentIssueCityId;
    private Integer nationalityId;
    private Integer maritalStatusId;

    private Boolean hasHouse;  // Usando Boolean en lugar de boolean
    private Boolean hasChildren;  // Usando Boolean en lugar de boolean

    private String secondaryEmail;
    private String phone;
    private Integer companyId;
    private Integer positionId;
    private Integer statusId;

    @NotEmpty(message = "Debe tener al menos un rol asignado")
    private List<Integer> roleIds;  // Validación de roles
}
