package com.datacenter.datateam.infrastructure.ports.out;

import java.util.Optional;

import com.datacenter.datateam.domain.models.User;

public interface UserPort {

    Optional<User> findByNuip(String nuip);
    boolean existsByNuip(String nuip);
    User save(User user);

}
