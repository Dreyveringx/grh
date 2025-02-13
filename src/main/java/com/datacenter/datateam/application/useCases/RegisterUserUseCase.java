package com.datacenter.datateam.application.useCases;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.domain.services.UserService;

@Component
@AllArgsConstructor
public class RegisterUserUseCase {
    private final UserService userService;

    public void execute(User user) {
        userService.registerUser(user);
    }
}
