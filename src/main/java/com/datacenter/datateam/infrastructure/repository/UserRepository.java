package com.datacenter.datateam.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacenter.datateam.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByNuip(String nuip);
}
