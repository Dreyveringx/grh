package com.datacenter.datateam.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;

@Service
@AllArgsConstructor
public class UserService {
    private final UserOutputPort userOutputPort;

    public void registerUser(User user) {
        // Lógica de negocio para registrar un usuario
        userOutputPort.save(user);
    }
}
