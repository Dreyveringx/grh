package com.datacenter.GRH.infrastructure.ports.out;

import com.datacenter.GRH.domain.models.User;
import java.util.Optional;

public interface PasswordRecoveryOutputPort {
    Optional<User> findByDocumentNumber(String documentNumber); // 🔥 Reemplaza findByEmail
    void saveUser(User user);
}
