package com.datacenter.GRH.infrastructure.ports.in;

import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;

public interface AuthInputPort {
    LoginResponse execute(LoginRequest request);
}
