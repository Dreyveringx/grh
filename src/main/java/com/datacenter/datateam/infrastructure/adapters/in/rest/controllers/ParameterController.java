package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.datateam.domain.services.ParameterService;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/parameters")
@RequiredArgsConstructor
public class ParameterController {
    private final ParameterService parameterService;

    @PostMapping
    public ResponseEntity<ParameterResponse> createParameter(@RequestBody ParameterRequest request) {
        return ResponseEntity.ok(parameterService.createParameter(request));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ParameterResponse>> getParametersByCategory(@PathVariable String category) {
        return ResponseEntity.ok(parameterService.getParametersByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterResponse> getParameterById(@PathVariable Long id) {
        return ResponseEntity.ok(parameterService.getParameterById(id));
    }
}

