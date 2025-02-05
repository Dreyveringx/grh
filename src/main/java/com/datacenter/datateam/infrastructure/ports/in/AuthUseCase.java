package com.datacenter.datateam.infrastructure.ports.in;

import java.util.Optional;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.AuthRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.AuthResponse;

public interface AuthUseCase {

    Optional<AuthResponse> authenticate(AuthRequest request);
    boolean registerUser(AuthRequest request);

}
