package com.datacenter.datateam.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.datacenter.datateam.domain.models.Parameter;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;

@Mapper(componentModel = "spring")
public interface ParameterMapper {

    @Mapping(target = "children", expression = "java(parameter.getChildren() != null ? parameter.getChildren().stream().map(this::toResponse).collect(Collectors.toList()) : null)")
    ParameterResponse toResponse(Parameter parameter);

    @Mapping(target = "children", ignore = true)
    Parameter toParameter(ParameterRequest request);
}
