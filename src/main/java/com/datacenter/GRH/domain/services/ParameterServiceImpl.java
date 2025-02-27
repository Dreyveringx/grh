package com.datacenter.GRH.domain.services;



import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Parameter;
import com.datacenter.GRH.infrastructure.ports.out.ParameterOutputPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParameterServiceImpl implements ParameterService {

    private final ParameterOutputPort parameterOutputPort;

    @Override
    public Parameter findById(Integer id) {
        return parameterOutputPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Parameter not found"));
    }
}
