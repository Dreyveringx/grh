package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El correo es obligatorio")
    private String email;

}
