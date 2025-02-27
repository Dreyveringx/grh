package com.datacenter.GRH.application.useCases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.GRH.infrastructure.ports.in.UserInputPort;

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

