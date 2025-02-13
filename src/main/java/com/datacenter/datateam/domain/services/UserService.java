package com.datacenter.datateam.domain.services;

import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.ports.out.UserOutputPort;

@Service
public class UserService implements UserOutputPort {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    public void registerUser(User user) {
        throw new UnsupportedOperationException("Unimplemented method 'registerUser'");
    }
}
