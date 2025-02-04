package com.datacenter.datateam.application;




import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.infrastructure.repository.UserRepository;
import com.datacenter.datateam.infrastructure.security.JwtUtil;

import java.util.Optional;
 
@Service
@RequiredArgsConstructor
public class AuthService {
 
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
 
    public Optional<String> authenticate(String nuip, String password) {
        return userRepository.findByNuip(nuip)
                .filter(user -> password.equals(user.getContraseña()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getNuip());
                    System.out.println("Generated JWT: " + token);
                    return token;
                });
    }
}
