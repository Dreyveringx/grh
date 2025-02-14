package com.datacenter.datateam.application.useCases;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.datacenter.datateam.application.exceptions.ResourceNotFoundException;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.ParameterRepository;
import com.datacenter.datateam.infrastructure.mappers.ParameterMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindParameterUseCase {
    private final ParameterRepository parameterRepository;

    public ParameterResponse getParameterById(Long id) {
        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parámetro no encontrado con ID: " + id));
        return ParameterMapper.INSTANCE.toResponse(parameter);
    }

    public List<ParameterResponse> getParametersByCategory(String category) {
        List<Parameter> parameters = parameterRepository.findByCategory(category);
        if (parameters.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron parámetros en la categoría: " + category);
        }
        return parameters.stream().map(ParameterMapper.INSTANCE::toResponse).collect(Collectors.toList());
    }
}

