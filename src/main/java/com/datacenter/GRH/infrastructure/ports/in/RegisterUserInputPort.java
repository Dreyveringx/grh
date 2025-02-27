package com.datacenter.GRH.infrastructure.ports.in;

import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.UserResponse;

public interface RegisterUserInputPort {
    UserResponse execute(RegisterUserRequest request);
}
