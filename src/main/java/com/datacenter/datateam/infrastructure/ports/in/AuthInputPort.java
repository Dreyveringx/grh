package com.datacenter.datateam.infrastructure.ports.in;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.LoginRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.LoginResponse;

public interface AuthInputPort {
    LoginResponse execute(LoginRequest request);
}
