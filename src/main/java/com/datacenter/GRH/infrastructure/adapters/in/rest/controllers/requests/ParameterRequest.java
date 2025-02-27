package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests;

import java.time.LocalDateTime;

import com.datacenter.GRH.domain.models.Parameter;

import lombok.*;

@Data
public class ParameterRequest {
    private String id;
    private Parameter parent;
    private String name;
    private String value;
    private String description;
    private LocalDateTime createdAt;
}

