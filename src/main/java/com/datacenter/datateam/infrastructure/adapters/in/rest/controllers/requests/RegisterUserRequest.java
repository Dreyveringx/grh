package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;


@Data
public class RegisterUserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 255, message = "El número de documento no puede superar los 255 caracteres")
    private String documentNumber;

    @NotNull(message = "El tipo de documento es obligatorio")
    private Integer documentTypeId;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private String birthDate;

    @NotNull(message = "La fecha de expedición del documento es obligatoria")
    private String documentIssueDate;

    @NotNull(message = "La ciudad de expedición es obligatoria")
    private Integer documentIssueCityId;

    @NotNull(message = "La nacionalidad es obligatoria")
    private Integer nationalityId;

    @NotNull(message = "El estado civil es obligatorio")
    private Integer maritalStatusId;

    private boolean hasHouse;
    private boolean hasChildren;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    private String secondaryEmail;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;

    private Integer companyId;
    private Integer positionId;

    @NotNull(message = "El estado del usuario es obligatorio")
    private Integer statusId;

    @NotEmpty(message = "Debe asignar al menos un rol")
    private List<Integer> roleIds;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
}

