package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import java.time.LocalDateTime;

import com.datacenter.datateam.domain.models.Company;

import lombok.Data;

@Data
public class CreateRoleRequest {
    private String id;
    private Company company;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
