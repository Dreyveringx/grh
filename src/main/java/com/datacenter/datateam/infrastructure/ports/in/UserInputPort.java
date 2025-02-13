package com.datacenter.datateam.infrastructure.ports.in;

import com.datacenter.datateam.domain.models.User;

public interface UserInputPort {
    void registerUser(User user);
}
