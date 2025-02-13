package com.datacenter.datateam.domain.services;

import org.springframework.stereotype.Service;
import com.datacenter.datateam.infrastructure.adapters.out.databases.DocumentTypeRepository;

import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.infrastructure.ports.out.DocumentTypeOutputPort;

@Service
public class DocumentTypeService implements DocumentTypeOutputPort{
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
            this.documentTypeRepository = documentTypeRepository;
    }
    @Override
    public void save(DocumentType documentType) {
        documentTypeRepository.save(documentType);
    }
    public void createDocumentType(DocumentType documentType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDocumentType'");
    }
}
