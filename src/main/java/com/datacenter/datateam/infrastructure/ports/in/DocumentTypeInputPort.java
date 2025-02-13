package com.datacenter.datateam.infrastructure.ports.in;

import com.datacenter.datateam.domain.models.DocumentType;

public interface DocumentTypeInputPort {
    void createDocumentType(DocumentType documentType);
}
