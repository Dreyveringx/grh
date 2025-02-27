package com.datacenter.GRH.infrastructure.ports.in;

import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.UpdatePasswordRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.PasswordUpdateResponse;

public interface PasswordInputPort {
    PasswordUpdateResponse updatePassword(UpdatePasswordRequest request);
}
