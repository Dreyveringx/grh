package com.datacenter.GRH.domain.services;

import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Parameter;

@Service
public interface ParameterService {
    public Parameter findById(Integer id);
}



