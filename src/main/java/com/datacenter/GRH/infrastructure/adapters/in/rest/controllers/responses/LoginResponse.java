package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses;

import com.datacenter.GRH.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private User user;

    // ✅ Nuevo constructor solo con el token
    public LoginResponse(String token) {
        this.token = token;
        this.user = null; // Si no hay usuario, se asigna null
    }
}
