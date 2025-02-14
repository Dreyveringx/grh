package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses;

import lombok.*;

@Data
@AllArgsConstructor
public class ParameterResponse {
    private Long id;
    private String category;
    private String name;
    private String value;
    private String description;
}
