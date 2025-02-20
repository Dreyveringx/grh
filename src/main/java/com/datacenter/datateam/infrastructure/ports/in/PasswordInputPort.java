package com.datacenter.datateam.infrastructure.ports.in;

import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.UpdatePasswordRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.PasswordUpdateResponse;

public interface PasswordInputPort {
    PasswordUpdateResponse updatePassword(UpdatePasswordRequest request);
}
