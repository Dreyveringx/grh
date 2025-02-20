package com.datacenter.datateam.infrastructure.adapters.out.databases;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserOutputPort {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByDocumentNumber(String documentNumber) {
        return userRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    };
    
}
