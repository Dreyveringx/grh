package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.datacenter.datateam.domain.models.Parameter;
import com.datacenter.datateam.infrastructure.ports.out.ParameterOutputPort;

@Component
public class ParameterOutputPortImpl implements ParameterOutputPort {
    @Override
    public void someMethod() {
    }

    @Override
    public Parameter save(Parameter parameter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Parameter> findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Parameter> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
