package com.datacenter.datateam.application.useCases;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.domain.services.DocumentTypeService;

@Component
@AllArgsConstructor
public class CreateDocumentTypeUseCase {
    private final DocumentTypeService documentTypeService;

    public void execute(DocumentType documentType) {
        documentTypeService.createDocumentType(documentType);
    }
}
