package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
