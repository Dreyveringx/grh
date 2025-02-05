package com.datacenter.datateam.application;




import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.model.User;
import com.datacenter.datateam.infrastructure.repository.UserRepository;
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
                .filter(user -> password.equals(user.getPassword()))
                .map(user -> {
                    String token = jwtUtil.generateToken(user.getNuip());
                    System.out.println("Generated JWT: " + token);
                    return token;
                });
    }

    public boolean registerUser(User user){
        if (userRepository.existsByNuip(user.getNuip())){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;
    }
}
