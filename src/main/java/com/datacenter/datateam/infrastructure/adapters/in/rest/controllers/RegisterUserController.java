package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import com.datacenter.datateam.application.useCases.RegisterUserUseCase;
import com.datacenter.datateam.application.exceptions.UserAlreadyExistsException;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.RegisterUserRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse response = registerUserUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        }
        return ResponseEntity.badRequest().body(errors.toString());
    }
}
