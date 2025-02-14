package com.datacenter.datateam.application.useCases;

import java.lang.reflect.Parameter;

import org.springframework.stereotype.Service;

import com.datacenter.datateam.application.exceptions.BadRequestException;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;
import com.datacenter.datateam.infrastructure.adapters.out.databases.ParameterRepository;
import com.datacenter.datateam.infrastructure.mappers.ParameterMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateParameterUseCase {
    private final ParameterRepository parameterRepository;

    public ParameterResponse createParameter(ParameterRequest request) {
        if (request.getCategory() == null || request.getName() == null) {
            throw new BadRequestException("La categoría y el nombre son obligatorios.");
        }

        Parameter parameter = ParameterMapper.INSTANCE.toEntity(request);
        parameter = parameterRepository.save(parameter);
        return ParameterMapper.INSTANCE.toResponse(parameter);
    }
}

