package com.datacenter.datateam.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.datacenter.datateam.domain.models.DocumentType;
import com.datacenter.datateam.infrastructure.adapters.in.rest.controllers.requests.CreateDocumentTypeRequest;

@Mapper
public interface DocumentTypeMapper {
    DocumentTypeMapper INSTANCE = Mappers.getMapper(DocumentTypeMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    DocumentType toDocumentType(CreateDocumentTypeRequest request);
}
