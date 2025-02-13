package com.datacenter.datateam.infrastructure.ports.out;

import com.datacenter.datateam.domain.models.DocumentType;

public interface DocumentTypeOutputPort {
    void save(DocumentType documentType);
}
