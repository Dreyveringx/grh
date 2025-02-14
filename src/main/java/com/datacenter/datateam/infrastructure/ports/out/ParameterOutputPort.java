package com.datacenter.datateam.infrastructure.ports.out;

import java.util.List;
import java.util.Optional;

import com.datacenter.datateam.domain.models.Parameter;

public interface ParameterOutputPort {
    Parameter save(Parameter parameter);
    Optional<Parameter> findById(Integer id);
    List<Parameter> findAll();
    void deleteById(Integer id);
}

