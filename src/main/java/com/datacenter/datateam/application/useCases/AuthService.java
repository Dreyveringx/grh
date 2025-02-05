package com.datacenter.datateam.application.useCases;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.application.exceptions.UsuarioInvalidoException;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.datateam.infrastructure.security.JwtUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public Optional<String> authenticate(String nuip, String password) {
        return userRepository.findByNuip(nuip)
        .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getNuip());
                    System.out.println("Generated JWT: " + token);
                    return token;
                });
    }

    public boolean registerUser(User user) {
        if (userRepository.existsByNuip(user.getNuip())) {
            throw new UsuarioInvalidoException("El usuario ya existe");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
