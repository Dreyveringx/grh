package com.datacenter.datateam.domain.services;

import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.Parameter;

@Service
public interface ParameterService {
    public Parameter findById(Integer id);
}



