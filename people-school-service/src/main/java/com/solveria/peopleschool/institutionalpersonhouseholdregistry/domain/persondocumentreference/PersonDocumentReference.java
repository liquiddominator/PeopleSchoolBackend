package com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference;

import java.time.LocalDateTime;

public record PersonDocumentReference(
        Long id,
        Long personId,
        String documentReferenceTypeCode,
        Long assetId,
        DocumentReferenceStatus referenceStatus,
        String notes,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {}
