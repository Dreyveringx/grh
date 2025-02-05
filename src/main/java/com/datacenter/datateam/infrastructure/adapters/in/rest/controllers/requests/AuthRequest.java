package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank(message = "El NUIP no puede estar vacío")
    private String nuip;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

}
