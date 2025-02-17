package com.datacenter.datateam.domain.services;

import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.Parameter;

@Service
public interface ParameterService {

    public default void executeService() {
            // Lógica del servicio
        }

    public Parameter findById(Integer id);
}



