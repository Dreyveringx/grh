package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String documentNumber;
    private String password;
}
