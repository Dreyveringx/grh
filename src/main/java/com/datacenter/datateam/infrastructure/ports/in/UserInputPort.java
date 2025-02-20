package com.datacenter.datateam.infrastructure.ports.in;

import com.datacenter.datateam.domain.models.User;

public interface UserInputPort {
    User registerUser(User user);
    User findByEmail(String email);
    void updateUser(User user);
}
