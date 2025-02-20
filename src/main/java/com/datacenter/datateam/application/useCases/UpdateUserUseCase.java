package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.ports.in.UserInputPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase implements UserInputPort {

    private final UserRepository userRepository;

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User registerUser(User user) {
        throw new UnsupportedOperationException("Operación no soportada en este caso de uso.");
    }

    @Override
    public User findByEmail(String email) {
        throw new UnsupportedOperationException("Operación no soportada en este caso de uso.");
    }
}

