package com.datacenter.datateam.infrastructure.adapters.out.databases;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacenter.datateam.domain.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByNuip(String nuip);
    boolean existsByNuip(String nuip);
}
