package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.datateam.application.useCases.RegisterUserUseCase;
import com.datacenter.datateam.domain.models.User;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody RegisterUserRequest request) {
        User user = userMapper.toUser(request);
        registerUserUseCase.execute(user);
        return ResponseEntity.ok().build();
    }
}
