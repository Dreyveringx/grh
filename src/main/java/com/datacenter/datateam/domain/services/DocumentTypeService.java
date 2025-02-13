package com.datacenter.datateam.domain.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.infrastructure.ports.out.DocumentTypeOutputPort;

@Service
@AllArgsConstructor
public class DocumentTypeService {
    private final DocumentTypeOutputPort documentTypeOutputPort;

    public void createDocumentType(DocumentType documentType) {
        // Lógica de negocio para crear un tipo de documento
        documentTypeOutputPort.save(documentType);
    }
}
