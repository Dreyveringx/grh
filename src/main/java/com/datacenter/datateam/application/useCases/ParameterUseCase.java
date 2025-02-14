package com.datacenter.datateam.application.useCases;

import java.util.List;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;

public interface ParameterUseCase {
    ParameterResponse createParameter(ParameterRequest request);
    List<ParameterResponse> getAllParameters();
    ParameterResponse getParameterById(Integer id);
    ParameterResponse updateParameter(Integer id, ParameterRequest request);
    void deleteParameter(Integer id);
}


