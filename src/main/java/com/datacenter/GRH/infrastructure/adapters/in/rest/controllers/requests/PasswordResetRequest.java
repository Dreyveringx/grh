package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String documentNumber;  // ✅ Usamos documentNumber en lugar de email
    private String token;
    private String newPassword;
    private String confirmPassword;
}
