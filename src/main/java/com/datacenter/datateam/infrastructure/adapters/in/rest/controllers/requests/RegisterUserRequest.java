package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String id;
    private String name;
    private String email;
    private String documentTypeId;
    private String roleId;
}
