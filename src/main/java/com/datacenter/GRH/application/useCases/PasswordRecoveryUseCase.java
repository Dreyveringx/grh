package com.datacenter.GRH.application.useCases;

import com.datacenter.GRH.domain.services.PasswordRecoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordRecoveryUseCase {
    
    private final PasswordRecoveryService passwordRecoveryService;

    public String generateRecoveryToken(String email) {
        return passwordRecoveryService.generateRecoveryToken(email);
    }

    public void resetPassword(String token, String newPassword) {
        passwordRecoveryService.resetPassword(token, newPassword);
    }
}
