package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // 🔥 Constructor vacío (necesario para frameworks como Jackson)
@AllArgsConstructor // 🔥 Constructor con parámetros (útil para pruebas y servicios)
public class ModuleRequest {

    @NotBlank(message = "El nombre del módulo es obligatorio")
    private String name;

    private String description;
}
