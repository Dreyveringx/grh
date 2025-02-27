package com.datacenter.GRH.infrastructure.ports.in;

import com.datacenter.GRH.domain.models.User;

public interface UserInputPort {
    User registerUser(User user);
    User findByEmail(String email);
    void updateUser(User user);
}
