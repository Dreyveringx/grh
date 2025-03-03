package com.datacenter.GRH.infrastructure.ports.in;

public interface PasswordRecoveryInputPort {
    String generateRecoveryToken(String email);
    void resetPassword(String token, String newPassword);
}
