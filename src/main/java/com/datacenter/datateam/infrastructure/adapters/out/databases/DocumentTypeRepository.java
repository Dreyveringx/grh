package com.datacenter.datateam.infrastructure.adapters.out.databases;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.infrastructure.ports.out.DocumentTypeOutputPort;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, String>, DocumentTypeOutputPort {
    // Métodos adicionales de consulta pueden ir aquí
}
