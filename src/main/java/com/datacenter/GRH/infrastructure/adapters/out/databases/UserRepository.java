package com.datacenter.GRH.infrastructure.adapters.out.databases;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.datacenter.GRH.domain.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDocumentNumber(String documentNumber);
    Optional<User> findByEmail(String email);
     @EntityGraph(attributePaths = {"roles", "roles.permissions", "company"})
    Optional<User> findUserWithRolesAndCompanyByDocumentNumber(String documentNumber);
}
