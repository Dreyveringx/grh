package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import lombok.Data;

@Data
public class CreateRoleRequest {
    private String id;
    private String name;
    private String description;
}
