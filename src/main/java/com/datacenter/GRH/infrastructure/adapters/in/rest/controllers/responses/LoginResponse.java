package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
