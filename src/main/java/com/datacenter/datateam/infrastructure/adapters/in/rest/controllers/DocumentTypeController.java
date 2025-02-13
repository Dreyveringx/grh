package com.datacenter.datateam.infrastructure.adapters.in.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datacenter.datateam.application.useCases.CreateDocumentTypeUseCase;
import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.CreateDocumentTypeRequest;
import com.datacenter.datateam.infrastructure.mappers.DocumentTypeMapper;

@RestController
@RequestMapping("/document-types")
@AllArgsConstructor
public class DocumentTypeController {
    private final CreateDocumentTypeUseCase createDocumentTypeUseCase;
    private final DocumentTypeMapper documentTypeMapper;

    @PostMapping
    public ResponseEntity<Void> createDocumentType(@RequestBody CreateDocumentTypeRequest request) {
        DocumentType documentType = documentTypeMapper.toDocumentType(request);
        createDocumentTypeUseCase.execute(documentType);
        return ResponseEntity.ok().build();
    }
}
