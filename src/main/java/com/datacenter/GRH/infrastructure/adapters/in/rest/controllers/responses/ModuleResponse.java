package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleResponse {
    private Long id;
    private String name;
    private String description;
}
