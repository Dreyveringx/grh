package com.datacenter.datateam.infrastructure.ports.out;

import com.datacenter.datateam.domain.models.User;

import java.util.Optional;

public interface UserOutputPort {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByDocumentNumber(String documentNumber);
}
