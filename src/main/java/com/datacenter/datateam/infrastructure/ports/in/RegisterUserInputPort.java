package com.datacenter.datateam.infrastructure.ports.in;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;

public interface RegisterUserInputPort {
    UserResponse execute(RegisterUserRequest request);
}
