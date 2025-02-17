package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private List<String> roles;
}
