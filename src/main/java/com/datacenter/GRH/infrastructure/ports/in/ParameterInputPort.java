package com.datacenter.GRH.infrastructure.ports.in;

import java.util.List;

import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;

public interface ParameterInputPort {
    ParameterResponse create(ParameterRequest request);
    List<ParameterResponse> getAll();
    ParameterResponse getById(Integer id);
    ParameterResponse update(Integer id, ParameterRequest request);
    void delete(Integer id);
}

