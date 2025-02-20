package com.datacenter.datateam.application.useCases;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.ports.in.UserInputPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByEmailUseCase implements UserInputPort {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario con email '" + email + "' no encontrado."));
    }

    @Override
    public User registerUser(User user) {
        throw new UnsupportedOperationException("Operación no soportada en este caso de uso.");
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Operación no soportada en este caso de uso.");
    }
}
