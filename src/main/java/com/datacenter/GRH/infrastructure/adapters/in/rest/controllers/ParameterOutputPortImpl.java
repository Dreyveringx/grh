package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.datacenter.GRH.domain.models.Parameter;
import com.datacenter.GRH.infrastructure.adapters.out.databases.ParameterRepository;
import com.datacenter.GRH.infrastructure.ports.out.ParameterOutputPort;

@Component
public class ParameterOutputPortImpl implements ParameterOutputPort {

    private final ParameterRepository parameterRepository;

    public ParameterOutputPortImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override 
    public Parameter save(Parameter parameter) {
        return parameterRepository.save(parameter);  // Guardamos el objeto en la base de datos
    }

    @Override
    public Optional<Parameter> findById(Integer id) {
        return parameterRepository.findById(id);  // Buscamos el objeto por ID
    }

    @Override
    public List<Parameter> findAll() {
        return parameterRepository.findAll();  // Obtenemos todos los parámetros
    }

    @Override
    public void deleteById(Integer id) {
        parameterRepository.deleteById(id);  // Eliminamos el parámetro por ID
    }
}
