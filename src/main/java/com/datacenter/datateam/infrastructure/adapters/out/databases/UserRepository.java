package com.datacenter.datateam.infrastructure.adapters.out.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Métodos adicionales de consulta pueden ir aquí
}
