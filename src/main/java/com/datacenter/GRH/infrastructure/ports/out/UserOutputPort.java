package com.datacenter.GRH.infrastructure.ports.out;

import java.util.Optional;
import com.datacenter.GRH.domain.models.User;

public interface UserOutputPort {
    User save(User user);
    Optional<User> findByDocumentNumber(String documentNumber);
    Optional<User> findByEmail(String email);
    Optional<User> findAdminWithCompanyAndRoles(String adminDocumentNumber);
}
