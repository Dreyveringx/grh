package com.datacenter.GRH.infrastructure.adapters.in.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.requests.UpdatePasswordRequest;
import com.datacenter.GRH.infrastructure.adapters.in.rest.controllers.responses.PasswordUpdateResponse;
import com.datacenter.GRH.infrastructure.ports.in.PasswordInputPort;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordInputPort passwordInputPort;

    @PutMapping("/update")
    public ResponseEntity<PasswordUpdateResponse> updatePassword(
            @Validated @RequestBody UpdatePasswordRequest request) {
        return ResponseEntity.ok(passwordInputPort.updatePassword(request));
    }
}
