package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String documentNumber;
    private String documentType;
    private String nationality;
    private String maritalStatus;
    private String company;
    private String position;
    private String status;
    private List<String> roles;
}



