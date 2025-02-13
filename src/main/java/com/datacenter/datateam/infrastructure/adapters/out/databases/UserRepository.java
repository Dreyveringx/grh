package com.datacenter.datateam.infrastructure.adapters.out.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;

@Repository
public interface UserRepository extends JpaRepository<User, String>, UserOutputPort {
    // Métodos adicionales de consulta pueden ir aquí
}
