package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import lombok.*;

@Data
public class ResetPasswordRequest {
    private String token;
    private String newPassword;
    private String confirmPassword;
}
