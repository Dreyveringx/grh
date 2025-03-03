package com.datacenter.GRH.domain.services;

import com.datacenter.GRH.domain.models.User;
import com.datacenter.GRH.infrastructure.adapters.out.databases.UserRepository;
import com.datacenter.GRH.infrastructure.adapters.out.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String generateRecoveryToken(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("⚠️ No existe un usuario con ese email.");
        }

        String token = jwtUtil.generateRecoveryToken(email);
        user.get().setResetToken(token);
        userRepository.save(user.get());

        return token;
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {
        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Token inválido o expirado."));

        if (!token.equals(user.getResetToken())) {
            throw new IllegalArgumentException("⚠️ Token inválido.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // 🔥 Se elimina el token después de usarlo
        userRepository.save(user);
    }
}
