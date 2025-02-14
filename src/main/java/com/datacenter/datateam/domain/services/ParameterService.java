package com.datacenter.datateam.domain.services;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.datacenter.datateam.application.exceptions.BadRequestException;
import com.datacenter.datateam.application.exceptions.ResourceNotFoundException;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.ParameterRepository;
import com.datacenter.datateam.infrastructure.mappers.ParameterMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParameterService {
    private final ParameterRepository parameterRepository;
    private final ParameterMapper parameterMapper;

    public ParameterResponse createParameter(ParameterRequest request) {
        if (request.getCategory() == null || request.getName() == null) {
            throw new BadRequestException("La categoría y el nombre son obligatorios.");
        }

        Parameter parameter = parameterMapper.toEntity(request);
        parameter = parameterRepository.save(parameter);
        return parameterMapper.toResponse(parameter);
    }

    public List<ParameterResponse> getParametersByCategory(String category) {
        List<Parameter> parameters = parameterRepository.findByCategory(category);
        if (parameters.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron parámetros en la categoría: " + category);
        }
        return parameters.stream().map(parameterMapper::toResponse).collect(Collectors.toList());
    }

    public ParameterResponse getParameterById(Long id) {
        Parameter parameter = parameterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parámetro no encontrado con ID: " + id));
        return parameterMapper.toResponse(parameter);
    }
}

