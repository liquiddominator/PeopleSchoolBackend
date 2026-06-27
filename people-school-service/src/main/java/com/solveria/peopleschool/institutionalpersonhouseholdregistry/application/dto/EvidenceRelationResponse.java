package com.solveria.peopleschool.institutionalpersonhouseholdregistry.application.dto;

import com.solveria.peopleschool.institutionalpersonhouseholdregistry.domain.personevidencerelation.PersonEvidenceRelation;
import java.time.LocalDateTime;

public record EvidenceRelationResponse(
        Long id,
        Long personId,
        Long householdId,
        String relatedContextType,
        String relatedContextRefId,
        String evidenceRoleCode,
        Long assetId,
        String evidenceStatus,
        String tenantId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime lastModifiedAt,
        String lastModifiedBy,
        Long version) {

    public static EvidenceRelationResponse from(PersonEvidenceRelation r) {
        return new EvidenceRelationResponse(
                r.id(),
                r.personId(),
                r.householdId(),
                r.relatedContextType(),
                r.relatedContextRefId(),
                r.evidenceRoleCode(),
                r.assetId(),
                r.evidenceStatus().name(),
                r.tenantId(),
                r.createdAt(),
                r.createdBy(),
                r.lastModifiedAt(),
                r.lastModifiedBy(),
                r.version());
    }
}
