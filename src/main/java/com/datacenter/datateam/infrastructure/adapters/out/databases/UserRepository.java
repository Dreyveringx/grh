package com.datacenter.datateam.infrastructure.adapters.out.databases;

import com.datacenter.datateam.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDocumentNumber(String documentNumber);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String token);
}
