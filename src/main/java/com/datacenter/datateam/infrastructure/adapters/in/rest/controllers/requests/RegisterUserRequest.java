package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests;

import java.util.List;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String documentNumber;
    private Integer documentTypeId;
    private Integer documentIssueCityId;
    private Integer nationalityId;
    private Integer maritalStatusId;
    private Integer companyId;
    private Integer positionId;
    private Integer statusId;
    private List<Integer> roleIds;
}
