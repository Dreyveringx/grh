package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import lombok.*;

@Data
public class ParameterRequest {
    private String category;
    private String name;
    private String value;
    private String description;
}

