package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datacenter.datateam.application.useCases.ParameterUseCase;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.ParameterRequest;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.responses.ParameterResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/parameters")
@RequiredArgsConstructor
public class ParameterController {
    private final ParameterUseCase parameterUseCase;

    @PostMapping
    public ResponseEntity<ParameterResponse> createParameter(@RequestBody ParameterRequest request) {
        return ResponseEntity.ok(parameterUseCase.createParameter(request));
    }

    @GetMapping
    public ResponseEntity<List<ParameterResponse>> getAllParameters() {
        return ResponseEntity.ok(parameterUseCase.getAllParameters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterResponse> getParameterById(@PathVariable Integer id) {
        return ResponseEntity.ok(parameterUseCase.getParameterById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParameterResponse> updateParameter(@PathVariable Integer id, @RequestBody ParameterRequest request) {
        return ResponseEntity.ok(parameterUseCase.updateParameter(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParameter(@PathVariable Integer id) {
        parameterUseCase.deleteParameter(id);
        return ResponseEntity.noContent().build();
    }
}


