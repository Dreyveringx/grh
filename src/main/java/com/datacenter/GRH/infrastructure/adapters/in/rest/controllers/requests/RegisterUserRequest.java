package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 8, max = 10, message = "El número de documento debe tener entre 8 y 10 caracteres")
    private String documentNumber;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @NotBlank(message = "El rol del usuario es obligatorio")
    private String role; // 🔥 Se añade el campo de rol
}
