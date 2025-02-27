package com.datacenter.GRH.application.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.Parameter;
import com.datacenter.GRH.domain.services.ParameterService;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;
import com.datacenter.GRH.infrastructure.mappers.ParameterMapper;
import com.datacenter.GRH.infrastructure.ports.out.ParameterOutputPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParameterUseCaseImpl implements ParameterUseCase {
    private final ParameterOutputPort parameterOutputPort;
    private final ParameterService parameterService;
    private final ParameterMapper parameterMapper;

    @Override
    public ParameterResponse createParameter(ParameterRequest request) {
        Parameter parameter = parameterMapper.toParameter(request);
        parameter = parameterOutputPort.save(parameter);
        return parameterMapper.toResponse(parameter);
    }

    @Override
    public List<ParameterResponse> getAllParameters() {
        return parameterOutputPort.findAll().stream()
                .map(parameterMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ParameterResponse getParameterById(Integer id) {
        Parameter parameter = parameterService.findById(id);
        return parameterMapper.toResponse(parameter);
    }

    @Override
    public ParameterResponse updateParameter(Integer id, ParameterRequest request) {
        Parameter parameter = parameterService.findById(id);

        parameter.setName(request.getName());
        parameter.setValue(request.getValue());
        parameter.setDescription(request.getDescription());

        parameter = parameterOutputPort.save(parameter);
        return parameterMapper.toResponse(parameter);
    }

    @Override
    public void deleteParameter(Integer id) {
        Parameter parameter = parameterService.findById(id);
        parameterOutputPort.deleteById(parameter.getId());
    }
}