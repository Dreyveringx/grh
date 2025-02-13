package com.datacenter.datateam.infrastructure.adapters.out.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datacenter.datateam.domain.models.DocumentType;
import java.util.List;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer>{

    // Método para buscar DocumentType por nombre
    List<DocumentType> findByName(String name);

    // Método para buscar DocumentType por descripción
    List<DocumentType> findByDescriptionContaining(String description);

    // Método personalizado con JPQL
    @Query("SELECT d FROM DocumentType d WHERE d.name LIKE %:name%")
    List<DocumentType> findByNameContaining(@Param("name") String name);
}
