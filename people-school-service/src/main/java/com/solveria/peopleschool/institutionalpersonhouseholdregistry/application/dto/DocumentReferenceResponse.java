package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.persondocumentreference.PersonDocumentReference;
import java.time.LocalDateTime;

public record DocumentReferenceResponse(
        Long id,
        Long personId,
        String documentReferenceTypeCode,
        Long assetId,
        String referenceStatus,
        String notes,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {

    public static DocumentReferenceResponse from(PersonDocumentReference r) {
        return new DocumentReferenceResponse(
                r.id(),
                r.personId(),
                r.documentReferenceTypeCode(),
                r.assetId(),
                r.referenceStatus().name(),
                r.notes(),
                r.tenantId(),
                r.createdAt(),
                r.createdBy(),
                r.lastModifiedAt(),
                r.lastModifiedBy(),
                r.version());
    }
}
