package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordRequest {
    @NotBlank
    private String documentNumber;

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
