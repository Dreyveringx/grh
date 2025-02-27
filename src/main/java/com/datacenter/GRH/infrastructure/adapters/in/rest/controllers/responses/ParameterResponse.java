package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses;

import java.util.List;

import com.datacenter.GRH.domain.models.Parameter;

import lombok.*;

@Data
@AllArgsConstructor
public class ParameterResponse {
    private Integer id;
    private Parameter parent;
    private String name;
    private String value;
    private String description;
    private List<ParameterResponse> children;
}
