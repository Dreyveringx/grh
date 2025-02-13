package com.datacenter.datateam.infrastructure.ports.out;

import com.datacenter.datateam.domain.models.User;

public interface UserOutputPort {
    void save(User user);
}
