package com.datacenter.datateam.infrastructure.mappers;

import java.lang.reflect.Parameter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;

@Mapper(componentModel = "spring")
public interface ParameterMapper {
    ParameterMapper INSTANCE = Mappers.getMapper(ParameterMapper.class);

    Parameter toEntity(ParameterRequest request);
    ParameterResponse toResponse(Parameter parameter);
}
